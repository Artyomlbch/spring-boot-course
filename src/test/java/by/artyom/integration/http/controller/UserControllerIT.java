package by.artyom.integration.http.controller;

import by.artyom.IT;
import by.artyom.spring.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IT
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class UserControllerIT {

    @Autowired
    private final MockMvc mockMvc;

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("user/users"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"))
                .andExpect(MockMvcResultMatchers.model().attribute("users", IsCollectionWithSize.hasSize(4)));

    }

    @Test
    public void create() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .param(UserCreateEditDto.Fields.username, "test@gmail.com")
                .param(UserCreateEditDto.Fields.firstname, "test")
                .param(UserCreateEditDto.Fields.lastname, "test")
                .param(UserCreateEditDto.Fields.role, "ADMIN")
                .param(UserCreateEditDto.Fields.companyId, "1")
                .param(UserCreateEditDto.Fields.birthDate, "2000-01-01")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }
}
