// 代码生成时间: 2025-09-12 04:13:50
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/theme")
public class ThemeSwitcherService {

    private Map<String, String> userThemes = new HashMap<>(); // Stores the current theme for each user

    /**
     * GET method to retrieve the current theme of a user.
     * @param username the username of the user
     * @return the current theme of the user
     */
    @GET
    @Path("/current")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCurrentTheme(@QueryParam("username") String username) {
        if (username == null || username.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Username is required.").build();
        }

        String theme = userThemes.getOrDefault(username, "default");
        return Response.ok(theme).build();
    }

    /**
     * POST method to switch the theme for a user.
     * @param username the username of the user
     * @param theme the new theme to set
     * @return the updated theme for the user
     */
    @POST
    @Path("/switch")
    @Produces(MediaType.TEXT_PLAIN)
    public Response switchTheme(@FormParam("username") String username, @FormParam("theme\) String theme) {
        if (username == null || username.isEmpty() || theme == null || theme.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Username and theme are required.").build();
        }

        userThemes.put(username, theme);
        return Response.ok(theme).build();
    }

}
