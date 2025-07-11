package by.artyom.integration.service;


import by.artyom.IT;
import by.artyom.spring.config.ApplicationConfiguration;
import by.artyom.spring.config.DatabaseProperties;
import by.artyom.spring.dto.CompanyReadDto;
import by.artyom.spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = ApplicationConfiguration.class)
//@ActiveProfiles("test") // чтобы искал Properties с -test
//@SpringBootTest(classes = ApplicationConfiguration.class)

@IT
@RequiredArgsConstructor
public class CompanyServiceIT {
    private static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;
    private final DatabaseProperties databaseProperties;

    @Test
    void findById() {
//        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);
//
//        assertTrue(actualResult.isPresent());
//
//        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID);
//
//        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }

}
