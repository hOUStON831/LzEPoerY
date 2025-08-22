// 代码生成时间: 2025-08-22 13:10:03
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/cart")
public class ShoppingCartService {

    // A map to simulate a database of shopping cart items
    private Map<String, Integer> cartItems = new HashMap<>();
    private Map<String, Integer> cartQuantities = new HashMap<>();

    // Initialize the cart with some items
    public ShoppingCartService() {
        cartItems.put("item1", 10);
        cartItems.put("item2", 20);
        cartItems.put("item3", 30);
    }

    @GET
    @Path("/items")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {
        return Response.ok(cartItems).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItemToCart(Map<String, Integer> item) {
        String itemId = item.keySet().iterator().next();
        int quantity = item.values().iterator().next();
        try {
            if (cartItems.containsKey(itemId)) {
                cartQuantities.merge(itemId, quantity, Integer::sum);
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Item not found").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding item to cart").build();
        }
        return Response.ok(cartQuantities).build();
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeItemFromCart(Map<String, Integer> item) {
        String itemId = item.keySet().iterator().next();
        int quantity = item.values().iterator().next();
        try {
            if (cartQuantities.containsKey(itemId) && cartQuantities.get(itemId) >= quantity) {
                cartQuantities.put(itemId, cartQuantities.get(itemId) - quantity);
                if (cartQuantities.get(itemId) == 0) {
                    cartQuantities.remove(itemId);
                }
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Item not found or invalid quantity").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error removing item from cart").build();
        }
        return Response.ok(cartQuantities).build();
    }

    @GET
    @Path("/total")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCartTotal() {
        if (cartQuantities.isEmpty()) {
            return Response.ok(0).build();
        }

        int total = 0;
        for (Map.Entry<String, Integer> entry : cartQuantities.entrySet()) {
            total += cartItems.get(entry.getKey()) * entry.getValue();
        }
        return Response.ok(total).build();
    }
}
