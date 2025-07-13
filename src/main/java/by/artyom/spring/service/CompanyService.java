package by.artyom.spring.service;

import by.artyom.spring.database.repository.CompanyRepository;
import by.artyom.spring.dto.CompanyReadDto;
import by.artyom.spring.listener.AccessType;
import by.artyom.spring.listener.EntityEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CompanyService(CompanyRepository companyRepository, ApplicationEventPublisher eventPublisher) {
        this.companyRepository = companyRepository;
        this.eventPublisher = eventPublisher;
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        System.out.println("CompanyService.findById method");

        return companyRepository.findById(id).map(entity -> {
            eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));

            return new CompanyReadDto(entity.getId(), null);
        });
    }

}
