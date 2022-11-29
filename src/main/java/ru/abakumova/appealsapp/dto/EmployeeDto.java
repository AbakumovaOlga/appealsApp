package ru.abakumova.appealsapp.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private String fio;
    private String email;
    private String username;
    private String password;
    private String manager_username;
}
