// 代码生成时间: 2025-08-20 10:44:19
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

// 定时任务调度器
@Path("/tasks")
public class ScheduledTaskManager {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    // 启动定时任务
    @Path("/start")
    @GET
    public String startTask() {
        if (isRunning.get()) {
            return "Task is already running.";
        }
        isRunning.set(true);
        scheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                // 定时任务代码
                System.out.println("Task executed at: " + System.currentTimeMillis());
            }
        }, 0, 5, TimeUnit.SECONDS);
        return "Task started successfully.";
    }

    // 停止定时任务
    @Path("/stop")
    @GET
    public String stopTask() {
        if (!isRunning.get()) {
            return "Task is not running.";
        }
        isRunning.set(false);
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(1, TimeUnit.MINUTES)) {
                scheduler.shutdownNow();
                if (!scheduler.awaitTermination(1, TimeUnit.MINUTES)) {
                    System.err.println("Scheduler did not terminate");
                }
            }
        } catch (InterruptedException ex) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
        return "Task stopped successfully.";
    }
}

// JAX-RS应用配置
public class AppConfig extends Application {
    @Override
    public java.util.Set<Class<?>> getClasses() {
        return java.util.Collections.singletonList(ScheduledTaskManager.class);
    }
}

// 启动JAX-RS服务器
public class Server {
    public static void main(String[] args) {
        new org.glassfish.jersey.server.ResourceConfig()
                .packages("com.example")
                .register(FreemarkerMvcFeature.class)
                .register(AppConfig.class)
                .start();
        System.out.println("Server started.");
    }
}