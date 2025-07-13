package by.artyom.integration.service;

import by.artyom.IT;
import by.artyom.spring.database.entity.Role;
import by.artyom.spring.database.repository.pool.ConnectionPool;
import by.artyom.spring.dto.UserCreateEditDto;
import by.artyom.spring.dto.UserReadDto;
import by.artyom.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
@Transactional
public class UserServiceIT {

    private final static Long USER_1 = 1L;
    private final static Integer COMPANY_1 = 1;
    private final UserService userService;

    @Test
    public void findAllTest() {
        List<UserReadDto> users = userService.findAll();
        assertThat(users).hasSize(5);
    }

    @Test
    public void findByIdTest() {
        Optional<UserReadDto> user = userService.findById(USER_1);
        assertTrue(user.isPresent());
        user.ifPresent(u -> assertEquals("ivan@gmail.com", u.getUsername()));
    }

    @Test
    public void createTest() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "test",
                "test",
                Role.ADMIN,
                COMPANY_1
        );

        UserReadDto actualUser = userService.create(userDto);

        assertEquals(userDto.getUsername(), actualUser.getUsername());
        assertEquals(userDto.getBirthDate(), actualUser.getBirthDate());
        assertEquals(userDto.getFirstname(), actualUser.getFirstname());
        assertEquals(userDto.getLastname(), actualUser.getLastname());
        assertEquals(userDto.getCompanyId(), actualUser.getCompany().id());
        assertSame(userDto.getRole(), actualUser.getRole());
    }

    @Test
    @Commit
    public void updateTest() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Testedit",
                "Testedit",
                Role.USER,
                COMPANY_1
        );

        Optional<UserReadDto> actualUser = userService.update(USER_1, userDto);
        assertTrue(actualUser.isPresent());

        actualUser.ifPresent(u -> {
            assertEquals(userDto.getUsername(), u.getUsername());
            assertEquals(userDto.getBirthDate(), u.getBirthDate());
            assertEquals(userDto.getFirstname(), u.getFirstname());
            assertEquals(userDto.getLastname(), u.getLastname());
            assertEquals(userDto.getCompanyId(), u.getCompany().id());
            assertSame(userDto.getRole(), u.getRole());
        });

    }

    @Test
    @Commit
    public void deleteTest() {
        assertFalse(userService.delete(-123L));
        assertTrue(userService.delete(USER_1));
    }
}
