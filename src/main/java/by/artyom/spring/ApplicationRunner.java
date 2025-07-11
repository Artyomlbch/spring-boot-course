package by.artyom.spring;

import by.artyom.spring.config.DatabaseProperties;
import by.artyom.spring.database.repository.pool.ConnectionPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.SpringProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationRunner.class, args);
//
//        System.out.println(context.getBean("connectionPool1"));
//
//        System.out.println(SpringProperties.getProperty("test.msg"));
//
//        System.out.println(context.getBean(DatabaseProperties.class));
//        Thread.yield();
    }

}
