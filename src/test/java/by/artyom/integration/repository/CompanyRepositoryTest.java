package by.artyom.integration.repository;

import by.artyom.IT;
import by.artyom.spring.database.entity.Company;
import by.artyom.spring.database.repository.CompanyRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor // автоматически @Autowire заинжектит EntityManager
@Transactional
//@Commit
public class CompanyRepositoryTest {

    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Test
    void checkFindByQueries() {
        companyRepository.findByName("Google");
        List<Company> companies = companyRepository.findAllByNameContainingIgnoreCase("a");

        assertThat(companies).hasSize(3);
    }

    @Test
    void delete() {
        Integer APPLE_ID = 5;
        Optional<Company> company = companyRepository.findById(APPLE_ID);

        assertTrue(company.isPresent());
        company.ifPresent(companyRepository::delete);

        entityManager.flush();

        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }

    @Test
//    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    void findById() {
        Company company = entityManager.find(Company.class, 1L);

        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    void save() {
        Company company = Company.builder()
                .name("Apple1")
                .locales(Map.of(
                        "en", "Apple1 company description",
                        "ru", "Apple1 описание компании"
                ))
                .build();

        entityManager.persist(company);
        assertNotNull(company.getId());
    }
}
