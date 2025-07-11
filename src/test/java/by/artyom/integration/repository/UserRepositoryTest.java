package by.artyom.integration.repository;


import by.artyom.IT;
import by.artyom.spring.database.entity.Role;
import by.artyom.spring.database.entity.User;
import by.artyom.spring.database.repository.UserRepository;
import by.artyom.spring.dto.IPersonalInfo;
import by.artyom.spring.dto.PersonalInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
@Transactional
public class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    public void checkPageable() {
        PageRequest pageable = PageRequest.of(0, 2, Sort.by("id"));

        Slice<User> users = userRepository.findAllBy(pageable);
        users.forEach(u -> System.out.println(u.getId()));

        while (users.hasNext()) {
            users = userRepository.findAllBy(users.nextPageable());
            users.forEach(u -> System.out.println(u.getId()));
        }
    }

    @Test
    public void testProjections() {
        List<PersonalInfo> users = userRepository.findAllByCompanyId(1, PersonalInfo.class);
        List<IPersonalInfo> usersTwo = userRepository.myFindByCompanyId(1);
        assertThat(users).hasSize(2);
    }

    @Test
    public void findFirstByCompanyIdIsNotNullOrderByIdTest() {
        Optional<User> user = userRepository.findFirstByCompanyIsNotNullOrderByIdDesc();
        assertTrue(user.isPresent());

        user.ifPresent(u -> assertEquals("Kate", u.getFirstname()));

    }

    @Test
    public void findByQueriesCheck() {
        List<User> users = userRepository.findAllByFirstnameContainingAndLastnameContaining("Iv", "I");
        List<User> usersByUsername = userRepository.findByUsername("sveta@gmail.com");

        System.out.println(users);

        assertFalse(users.isEmpty());
        assertFalse(usersByUsername.isEmpty());
    }

    @Test
    public void updateRoleTest() {
        int result = userRepository.updateRole(Role.USER, 1L, 5L);

        assertEquals(2, result);
    }

}
