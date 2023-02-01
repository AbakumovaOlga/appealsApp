package ru.abakumova.appealsapp.services;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.abakumova.appealsapp.dto.RegisterDto;
import ru.abakumova.appealsapp.exceptions.NoEntityException;
import ru.abakumova.appealsapp.mappers.AccountMapper;
import ru.abakumova.appealsapp.security.JwtProvider;
import ru.abakumova.appealsapp.models.Account;
import ru.abakumova.appealsapp.repositories.AccountRepository;


@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final AccountMapper accountMapper;


    @Override
    public UserDetails loadUserByUsername(String username){
        try {
            return accountRepository.findByUsername(username).orElseThrow(() -> new NoEntityException(Account.class));
        } catch (NoEntityException e) {
            throw new RuntimeException(e);
        }
    }

    public void register(RegisterDto registerDto) {
        Account account = accountMapper.fromRegisterDto(registerDto);
        account.setPassword(encoder.encode(account.getPassword()));
        accountRepository.save(account);
    }


    public String auth(String username, String password) throws NoEntityException {
        Account profile = accountRepository.findByUsername(username).orElseThrow(() -> new NoEntityException(Account.class));
        if (encoder.matches(password, profile.getPassword())) {
            return jwtProvider.generateToken(username);
        }
        return null;
    }
}
