package ru.abakumova.appealsapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abakumova.appealsapp.models.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByUsername(String username);

}
