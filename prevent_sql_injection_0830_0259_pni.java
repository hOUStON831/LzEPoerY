// 代码生成时间: 2025-08-30 02:59:47
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Path("/sql")
public class PreventSqlInjection {

    // JDBC variables for database connection
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USER = "username";
    private static final String PASS = "password";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    // Function to get data based on user input
    @GET
    @Path("/getData")
    public Response getData(@QueryParam("id") String id) {
        StringBuilder response = new StringBuilder();
        try {
            // Load JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);

            // Prepare SQL statement to prevent SQL injection
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, id);

            // Execute query and process results
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                response.append("ID: ").append(rs.getString("id")).append(", ")
                        .append("Name: ").append(rs.getString("name")).append("
");
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("JDBC Driver not found.").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("SQL error: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred: " + e.getMessage()).build();
        }

        return Response.status(Response.Status.OK).entity(response.toString()).build();
    }
}
