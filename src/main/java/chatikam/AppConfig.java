package chatikam;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.SimpleHealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.AbstractDataSourceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

@Configuration
public class AppConfig extends AbstractDataSourceConfiguration {

    private long validationInterval = 30000;
    private org.apache.tomcat.jdbc.pool.DataSource pool;

    @Bean(/*destroyMethod = "close"*/)
    public DataSource dataSource() {
        this.pool = new org.apache.tomcat.jdbc.pool.DataSource();
        this.pool.setDriverClassName(getDriverClassName());
        this.pool.setUrl(getUrl());
        if (getUsername() != null) {
            this.pool.setUsername(getUsername());
        }
        if (getPassword() != null) {
            this.pool.setPassword(getPassword());
        }
        this.pool.setInitialSize(getInitialSize());
        this.pool.setMaxActive(getMaxActive());
        this.pool.setMaxIdle(getMaxIdle());
        this.pool.setMinIdle(getMinIdle());
        this.pool.setTestOnBorrow(isTestOnBorrow());
        this.pool.setTestOnReturn(isTestOnReturn());
        this.pool.setTestWhileIdle(isTestWhileIdle());
        if (getTimeBetweenEvictionRunsMillis() != null) {
            this.pool
                    .setTimeBetweenEvictionRunsMillis(getTimeBetweenEvictionRunsMillis());
        }
        if (getMinEvictableIdleTimeMillis() != null) {
            this.pool.setMinEvictableIdleTimeMillis(getMinEvictableIdleTimeMillis());
        }
        this.pool.setValidationQuery(getValidationQuery());
        this.pool.setValidationInterval(this.validationInterval);
        if (getMaxWaitMillis() != null) {
            this.pool.setMaxWait(getMaxWaitMillis());
        }
        return new Log4jdbcProxyDataSource(this.pool);
    }

    @PreDestroy
    public void close() {
        if (this.pool != null) {
            this.pool.close();
        }
    }

    @ConditionalOnClass(name = "org.hsqldb.jdbcDriver")
    @Bean
    HealthIndicator<?> healthIndicator(DataSource dataSource) {
        // http://javasplitter.blogspot.jp/2011/01/keep-alive-query-in-hsqldb.html
        SimpleHealthIndicator healthIndicator = new SimpleHealthIndicator();
        healthIndicator.setDataSource(dataSource);
        healthIndicator.setQuery("SELECT now() FROM INFORMATION_SCHEMA.SYSTEM_TABLES LIMIT 1");
        return healthIndicator;
    }
}
