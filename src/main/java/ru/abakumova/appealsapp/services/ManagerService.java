package ru.abakumova.appealsapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abakumova.appealsapp.dto.ManagerDto;
import ru.abakumova.appealsapp.dto.RegisterDto;
import ru.abakumova.appealsapp.exceptions.NoEntityException;
import ru.abakumova.appealsapp.mappers.AccountMapper;
import ru.abakumova.appealsapp.mappers.ManagerMapper;
import ru.abakumova.appealsapp.models.Account;
import ru.abakumova.appealsapp.models.Manager;
import ru.abakumova.appealsapp.models.enums.AccountRole;
import ru.abakumova.appealsapp.repositories.ManagerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;
    private final AccountMapper accountMapper;
    private final AccountService accountService;
    @Transactional
    public void create(ManagerDto managerDto) {
        RegisterDto registerDto=new RegisterDto();
        registerDto.setPassword(managerDto.getPassword());
        registerDto.setUsername(managerDto.getUsername());
        registerDto.setRole(AccountRole.MANAGER);
        accountService.register(registerDto);
        Manager manager = managerMapper.fromManagerDto(managerDto);
        Manager savedManager = managerRepository.save(manager);
    }

    public Manager findByUsername(String username) throws NoEntityException {
        //TODO: add exc
        return managerRepository.findByUsername(username).orElseThrow(() -> new NoEntityException(Manager.class));
    }

    public List<Manager> getManagers() {
        return managerRepository.findAll();
    }
}
