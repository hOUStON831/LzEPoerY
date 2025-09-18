// 代码生成时间: 2025-09-18 08:42:02
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.StdScheduler;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.Set;

@Path("/scheduler")
public class ScheduledTaskScheduler extends ResourceConfig {
    
    public ScheduledTaskScheduler() {
        packages("com.yourpackage.services");
        register(FreemarkerMvcFeature.class);
    }
    
    // Quartz Job class
    public static class HelloJob implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Hello Job is running at: " + new Date());
        }
    }
    
    // Initialize and start the Quartz scheduler
    public static void initializeScheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        
        // Define the job and set its properties
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myJob", "group1")
                .build();
        
        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/40 * * * * ?