// 代码生成时间: 2025-08-14 17:47:40
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// 使用JERSEY框架创建的定时任务调度器服务
@Path("/scheduler")
public class ScheduledTaskManager {
    
    // 使用ScheduledExecutorService进行定时任务调度
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // 用于计数的原子变量
    private final AtomicInteger counter = new AtomicInteger(0);

    public ScheduledTaskManager() {
        // 初始化时，启动定时任务
        scheduleTasks();
    }

    // JERSEY框架的资源方法，返回当前计数器的值
    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        return Integer.toString(counter.get());
    }

    // 定义定时执行的任务
    private void scheduleTasks() {
        // 每5秒执行一次任务
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Task executed at: " + System.currentTimeMillis() + " - Counter: " + counter.incrementAndGet());
        }, 0, 5, TimeUnit.SECONDS);
    }

    // 用于优雅地关闭定时任务调度器
    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException ex) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
