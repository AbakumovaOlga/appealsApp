package ru.abakumova.appealsapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.abakumova.appealsapp.dto.LoginDto;
import ru.abakumova.appealsapp.dto.RegisterDto;
import ru.abakumova.appealsapp.mappers.AccountMapper;
import ru.abakumova.appealsapp.models.Account;
import ru.abakumova.appealsapp.services.AccountService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterDto registerDto) {
        Account account = accountMapper.fromRegisterDto(registerDto);
        accountService.register(account);
    }

    @PostMapping("/auth")
    public String auth(@RequestBody LoginDto dto) {
        return accountService.auth(dto.getUsername(), dto.getPassword());
    }

}
