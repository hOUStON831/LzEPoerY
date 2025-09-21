// 代码生成时间: 2025-09-21 17:47:18
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// CacheService 类提供了基于内存的缓存功能，用于存储和检索对象。
@Path("/cache")
public class CacheService {

    private final ConcurrentHashMap<String, CacheItem> cacheStore = new ConcurrentHashMap<>();
    private final long DEFAULT_EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(5); // 默认缓存过期时间为5分钟

    // 获取缓存项
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFromCache(String key) {
        CacheItem cacheItem = cacheStore.get(key);
        if (cacheItem == null || cacheItem.isExpired()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Cache item not found or expired.").build();
        } else {
            return Response.ok(cacheItem.getValue()).build();
        }
    }

    // 设置缓存项
    @GET
    @Path("/set")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setToCache(String key, String value) {
        cacheStore.put(key, new CacheItem(value, System.currentTimeMillis(), DEFAULT_EXPIRATION_TIME));
        return Response.ok("Cache item set successfully.").build();
    }

    // 缓存项类，存储值和过期时间
    private static class CacheItem {
        private final String value;
        private final long expirationTime;
        private final long createTime;

        public CacheItem(String value, long createTime, long expirationTime) {
            this.value = value;
            this.createTime = createTime;
            this.expirationTime = expirationTime;
        }

        public String getValue() {
            return value;
        }

        // 检查缓存项是否已过期
        public boolean isExpired() {
            return System.currentTimeMillis() > createTime + expirationTime;
        }
    }
}
