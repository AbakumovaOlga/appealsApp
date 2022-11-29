package ru.abakumova.appealsapp.dto;

import lombok.Data;

import javax.validation.constraints.Email;


@Data
public class ManagerDto {
    private String fio;
    @Email
    private String email;
    private String username;
    private String password;
}
