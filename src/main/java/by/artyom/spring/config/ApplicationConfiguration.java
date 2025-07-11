package by.artyom.spring.config;

import by.artyom.spring.database.repository.CompanyRepository;
import by.artyom.spring.database.repository.pool.ConnectionPool;
import by.artyom.spring.service.CompanyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool connectionPool1(@Value("${db.username}") String username) {
        return new ConnectionPool(username, "root", 20, "url");
    }

    @Bean
    public ConnectionPool connectionPool2() {
        return new ConnectionPool("mysql", "123", 12, "---");
    }


}
