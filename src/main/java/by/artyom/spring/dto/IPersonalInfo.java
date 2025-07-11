package by.artyom.spring.dto;

import java.time.LocalDate;

public interface IPersonalInfo {
    String getFirstname();
    String getLastname();
    LocalDate getBirthdate();
}
