// 代码生成时间: 2025-09-22 15:31:30
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/theme")
public class ThemeSwitchingApplication {

    private String currentTheme;

    public ThemeSwitchingApplication() {
        // Default theme set to 'light'
        this.currentTheme = "light";
    }

    /**
     * This method handles GET requests to /theme and returns the current theme.
     * @return The current theme as a string.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCurrentTheme() {
        try {
            return Response.ok(currentTheme).build();
        } catch (Exception e) {
            // Log the exception and return a 500 error code
            // Log code not shown for brevity
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving current theme").build();
        }
    }

    /**
     * This method handles POST requests to /theme and switches the theme.
     * @param theme The new theme to switch to.
     * @return A response indicating the switch was successful.
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response switchTheme(String theme) {
        try {
            if (theme == null || theme.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("No theme provided").build();
            }
            // Validate the theme to ensure it's one of the supported themes
            if (!theme.equals("light") && !theme.equals("dark")) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Unsupported theme").build();
            }
            currentTheme = theme;
            return Response.ok("Theme switched to: " + theme).build();
        } catch (Exception e) {
            // Log the exception and return a 500 error code
            // Log code not shown for brevity
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error switching theme").build();
        }
    }
}
