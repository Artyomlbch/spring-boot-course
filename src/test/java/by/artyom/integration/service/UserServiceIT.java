package by.artyom.integration.service;

import by.artyom.IT;
import by.artyom.spring.database.repository.pool.ConnectionPool;
import by.artyom.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

@IT
@RequiredArgsConstructor
public class UserServiceIT {

    private final UserService userService;

    @Test
    void test() {

    }

}
