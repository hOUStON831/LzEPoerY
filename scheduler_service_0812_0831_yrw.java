// 代码生成时间: 2025-08-12 08:31:23
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.servlet.ServletContainer;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.Date;

@WebServlet(urlPatterns = "/scheduler", loadOnStartup = 1)
public class SchedulerService extends ResourceConfig {

    private static final String SCHEDULER_SERVICE = "SchedulerService";

    public SchedulerService() {
        packages(SCHEDULER_SERVICE);
        register(FreemarkerMvcFeature.class);
    }

    public static void main(String[] args) throws ServletException, SchedulerException {
        // Initialize the scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob", "group1")
                .build();

        // Trigger the job to run now, and then repeat every 5 seconds
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);

        // Start the scheduler
        scheduler.start();
    }
}

/**
 * MyJob.java
 * A simple Quartz job class to perform scheduled tasks.
 */
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // This method will be called when the scheduled job runs
        System.out.println("Job executed at: " + new Date());
        try {
            // Insert your business logic here
            // For example, you could perform a database operation, send an email, etc.
        } catch (Exception e) {
            // Handle any exceptions that occur during job execution
            e.printStackTrace();
        }
    }
}