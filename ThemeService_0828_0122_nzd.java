// 代码生成时间: 2025-08-28 01:22:33
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpSession;

@Path("/theme")
public class ThemeService {

    private static final String THEME_KEY = "theme";
    private static final String LIGHT_THEME = "light";
    private static final String DARK_THEME = "dark";

    @GET
    @Path("/switch")
    public Response switchTheme(@QueryParam("theme\) String theme, @Context HttpSession session) {
        try {
            if (theme == null || (!theme.equals(LIGHT_THEME) && !theme.equals(DARK_THEME))) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid theme specified.").build();
            }

            // Store the theme in the session
            session.setAttribute(THEME_KEY, theme);

            // Return a success response
            return Response.ok("Theme switched to: " + theme).build();
        } catch (Exception e) {
            // Handle any unexpected errors
            return Response.serverError().entity("An error occurred while switching themes.").build();
        }
    }

    @GET
    @Path("/get")
    public Response getCurrentTheme(@Context HttpSession session) {
        try {
            String currentTheme = (String) session.getAttribute(THEME_KEY);
            if (currentTheme == null) {
                // Default to light theme if not set
                currentTheme = LIGHT_THEME;
            }

            // Return the current theme
            return Response.ok("Current theme is: " + currentTheme).build();
        } catch (Exception e) {
            // Handle any unexpected errors
            return Response.serverError().entity("An error occurred while retrieving the theme.").build();
        }
    }
}
