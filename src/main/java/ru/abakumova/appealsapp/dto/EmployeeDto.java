package ru.abakumova.appealsapp.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;


@Data
public class EmployeeDto {
    private String fio;
    @Email
    private String email;
    private String username;
    private String password;
    private String manager_username;
}
