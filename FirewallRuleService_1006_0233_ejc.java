// 代码生成时间: 2025-10-06 02:33:24
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A RESTful service to manage firewall rules using JERSEY framework.
 */
@ApplicationPath("/firewall")
public class FirewallRuleService extends Application {

    private Map<Integer, String> firewallRules = Collections.synchronizedMap(new HashMap<>());
    private int ruleId = 1;

    /**
     * Get all firewall rules.
     * @return A JSON array of firewall rules.
     */
    @Path("/rules")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRules() {
        return Response.ok(firewallRules.values()).build();
    }

    /**
     * Get a specific firewall rule by ID.
     * @param id The ID of the firewall rule.
     * @return A JSON object of the firewall rule if found, otherwise a 404 error.
     */
    @Path("/rules/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRuleById(@PathParam("id") int id) {
        String rule = firewallRules.get(id);
        if (rule != null) {
            return Response.ok(rule).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Rule not found.").build();
        }
    }

    /**
     * Add a new firewall rule.
     * @param rule The JSON object containing the firewall rule details.
     * @return A JSON object of the newly created rule or an error message if the rule is invalid.
     */
    @Path("/rules")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRule(String rule) {
        try {
            firewallRules.put(ruleId, rule);
            return Response.status(Response.Status.CREATED).entity(
                    String.format("Rule created with ID: %d", ruleId++)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid rule.").build();
        }
    }

    /**
     * Update an existing firewall rule by ID.
     * @param id The ID of the firewall rule to update.
     * @param rule The JSON object containing the updated firewall rule details.
     * @return A JSON object of the updated rule or an error message if the rule is not found.
     */
    @Path("/rules/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRule(@PathParam("id") int id, String rule) {
        if (firewallRules.containsKey(id)) {
            firewallRules.put(id, rule);
            return Response.ok(String.format("Rule updated with ID: %d", id)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Rule not found.").build();
        }
    }
}
