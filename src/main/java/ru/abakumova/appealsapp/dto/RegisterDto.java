package ru.abakumova.appealsapp.dto;

import lombok.Data;
import ru.abakumova.appealsapp.models.enums.AccountRole;

@Data
public class RegisterDto {

        private String username;
        private String password;
        private AccountRole role;

}
