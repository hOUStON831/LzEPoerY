// 代码生成时间: 2025-09-08 10:13:43
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
# TODO: 优化性能
import java.util.UUID;

// ShoppingCartItem represents an item in the shopping cart
class ShoppingCartItem {
    private String id;
    private String productId;
    private int quantity;
    
    public ShoppingCartItem(String productId, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.productId = productId;
# 添加错误处理
        this.quantity = quantity;
# TODO: 优化性能
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
# 增强安全性
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
# TODO: 优化性能
    }
}

// ShoppingCartService handles the shopping cart operations
@Path("/cart")
public class ShoppingCartService {
    private Map<String, ShoppingCartItem> cartItems = new HashMap<>();
    private Map<String, Integer> productQuantities = new HashMap<>();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, ShoppingCartItem> getCartItems() {
        return cartItems;
    }
    
    @POST
# 增强安全性
    @Path("/item")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ShoppingCartItem addItemToCart(ShoppingCartItem item) {
        if (!productQuantities.containsKey(item.getProductId()) ||
            productQuantities.get(item.getProductId()) > 0) {
            cartItems.put(item.getId(), item);
            productQuantities.put(item.getProductId(), productQuantities.getOrDefault(item.getProductId(), 0) - item.getQuantity());
            return item;
        } else {
            throw new WebApplicationException("Product out of stock", 409);
        }
    }
    
    @PUT
    @Path("/item/{id}")
# 添加错误处理
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ShoppingCartItem updateItemQuantity(@PathParam("id") String id, ShoppingCartItem newItem) {
# NOTE: 重要实现细节
        if (cartItems.containsKey(id)) {
            cartItems.put(id, newItem);
            productQuantities.put(newItem.getProductId(), productQuantities.getOrDefault(newItem.getProductId(), 0) - newItem.getQuantity());
            return newItem;
        } else {
            throw new WebApplicationException("Item not found in cart", 404);
        }
# FIXME: 处理边界情况
    }
# 添加错误处理
    
    @DELETE
    @Path("/item/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ShoppingCartItem removeItemFromCart(@PathParam("id") String id) {
        if (cartItems.containsKey(id)) {
            ShoppingCartItem item = cartItems.remove(id);
            productQuantities.put(item.getProductId(), productQuantities.getOrDefault(item.getProductId(), 0) + item.getQuantity());
            return item;
        } else {
            throw new WebApplicationException("Item not found in cart", 404);
        }
# FIXME: 处理边界情况
    }
}
