package ru.abakumova.appealsapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.abakumova.appealsapp.configs.JwtProvider;
import ru.abakumova.appealsapp.models.Account;
import ru.abakumova.appealsapp.repositories.AccountRepository;



@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username);
    }

    public void register(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));
        accountRepository.save(account);
    }


    public String auth(String username, String password) {
        Account profile = accountRepository.findByUsername(username);
        if (encoder.matches(password, profile.getPassword())) {
            return jwtProvider.generateToken(username);
        }
        return null;
    }
}
