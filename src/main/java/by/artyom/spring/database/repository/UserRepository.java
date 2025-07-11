package by.artyom.spring.database.repository;

import by.artyom.spring.bpp.InjectBean;
import by.artyom.spring.database.entity.Role;
import by.artyom.spring.database.entity.User;
import by.artyom.spring.database.repository.pool.ConnectionPool;
import by.artyom.spring.dto.IPersonalInfo;
import by.artyom.spring.dto.PersonalInfo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Slf4j
//@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> clazz);

    Slice<User> findAllBy(Pageable pageable);

    @Query(value = "SELECT u.firstname, u.lastname, u.birth_date FROM users u WHERE company_id = :companyId",
            nativeQuery = true)
    List<IPersonalInfo> myFindByCompanyId(Integer companyId);

    Optional<User> findFirstByCompanyIsNotNullOrderByIdDesc();

    @Query("select u from User u " +
            "where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllByFirstnameContainingAndLastnameContaining(String firstname, String lastname);

    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username", nativeQuery = true)
    List<User> findByUsername(String username);

    @Modifying
    @Query("update User u set u.role = :role where u.id in (:ids)")
    int updateRole(Role role, Long... ids);

}
