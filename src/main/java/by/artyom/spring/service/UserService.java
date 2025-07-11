package by.artyom.spring.service;

import by.artyom.spring.database.repository.UserRepository;
import by.artyom.spring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ToString
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

}
