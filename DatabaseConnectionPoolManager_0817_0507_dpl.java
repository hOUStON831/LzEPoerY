// 代码生成时间: 2025-08-17 05:07:24
import javax.sql.DataSource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.server.ServerProperties;
import org.h2.jdbcx.JdbcDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConnectionPoolManager {

    private static HikariDataSource dataSource;

    // 初始化数据库连接池
    public static void initPool(String jdbcURL, String username, String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcURL);
        config.setUsername(username);
        config.setPassword(password);
        config.setMinimumIdle(5); // 最小空闲连接数
        config.setMaximumPoolSize(20); // 最大连接数
        config.setIdleTimeout(30000); // 空闲连接超时时间, 单位为毫秒
        config.setConnectionTimeout(30000); // 连接超时时间, 单位为毫秒
        config.setDriverClassName("org.h2.Driver"); // 指定JDBC驱动类

        dataSource = new HikariDataSource(config);
    }

    // 获取数据源
    public static DataSource getDataSource() {
        return dataSource;
    }

    // 关闭数据库连接池
    public static void closePool() {
        if (dataSource != null && dataSource.isRunning()) {
            dataSource.close();
        }
    }
}
