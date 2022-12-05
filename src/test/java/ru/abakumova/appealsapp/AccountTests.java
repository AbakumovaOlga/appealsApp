package ru.abakumova.appealsapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.abakumova.appealsapp.dto.RegisterDto;
import ru.abakumova.appealsapp.models.Account;
import ru.abakumova.appealsapp.models.enums.AccountRole;
import ru.abakumova.appealsapp.repositories.AccountRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AccountTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private AccountRepository accountRepository;

    @Test
    void registrationWorksThroughAllLayers() throws Exception {
        RegisterDto account = new RegisterDto();
        account.setRole(AccountRole.ADMIN);
        account.setPassword("test_password");
        account.setUsername("test_username");

        mockMvc.perform(post("/api/account/register", 42L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isOk());

        Account accountEntity = accountRepository.findByUsername("test_username");
        assertThat(accountEntity.getRole()).isEqualTo(AccountRole.ADMIN);
    }
}
