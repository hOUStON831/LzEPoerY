// 代码生成时间: 2025-08-21 10:11:27
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Jersey配置类，配置Jersey和Freemarker
 */
@ApplicationPath("/api")
public class DatabaseConnectionPoolManager extends ResourceConfig {

    public static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    public static final String DB_USER = "your_username";
    public static final String DB_PASSWORD = "your_password";

    /**
     * 初始化Jersey和Freemarker
     */
    public DatabaseConnectionPoolManager() {
        register(FreemarkerMvcFeature.class);
        packages("com.example.resources");
    }

    /**
     * 获取数据库连接
     * @return 数据库连接对象
     */
    public static Connection getConnection() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        DataSource dataSource = new HikariDataSource(config);
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            // 错误处理
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭数据库连接
     * @param connection 数据库连接对象
     */
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // 错误处理
            e.printStackTrace();
        }
    }

    // 主函数，用于测试数据库连接池
    public static void main(String[] args) {
        Connection connection = getConnection();
        if (connection != null) {
            System.out.println("Database connection established.");
        } else {
            System.out.println("Failed to establish database connection.");
        }
        closeConnection(connection);
    }
}
