package ru.abakumova.appealsapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abakumova.appealsapp.models.Manager;
import ru.abakumova.appealsapp.repositories.ManagerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;

    @Transactional
    public void create(Manager manager) {
        Manager savedManager = managerRepository.save(manager);
    }

    public Manager findByUsername(String username) {
        //TODO: add exc
        return managerRepository.findByUsername(username);
    }

    public List<Manager> getManagers() {
        return managerRepository.findAll();
    }
}
