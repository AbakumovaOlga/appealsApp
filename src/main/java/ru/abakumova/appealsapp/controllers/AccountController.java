package ru.abakumova.appealsapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.abakumova.appealsapp.dto.LoginDto;
import ru.abakumova.appealsapp.dto.RegisterDto;
import ru.abakumova.appealsapp.exceptions.NoEntityException;
import ru.abakumova.appealsapp.services.AccountService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterDto registerDto) {
        accountService.register(registerDto);
    }

    @PostMapping("/auth")
    public String auth(@RequestBody LoginDto dto) throws NoEntityException {
        return accountService.auth(dto.getUsername(), dto.getPassword());
    }

}
