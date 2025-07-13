package by.artyom.spring.mapper;

import by.artyom.spring.database.entity.Company;
import by.artyom.spring.database.entity.User;
import by.artyom.spring.database.repository.CompanyRepository;
import by.artyom.spring.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final CompanyRepository companyRepository;

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);

        return user;
    }

    private Company getCompany(Integer companyId) {
        return Optional.ofNullable(companyId).flatMap(companyRepository::findById)
                .orElse(null);
    }

    private void copy(UserCreateEditDto fromObject, User toObject) {
        toObject.setUsername(fromObject.getUsername());
        toObject.setFirstname(fromObject.getFirstname());
        toObject.setLastname(fromObject.getLastname());
        toObject.setBirthDate(fromObject.getBirthDate());
        toObject.setRole(fromObject.getRole());
        toObject.setCompany(getCompany(fromObject.getCompanyId()));
    }
}
