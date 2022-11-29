package ru.abakumova.appealsapp.models;

import javax.persistence.*;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;
import ru.abakumova.appealsapp.models.enums.AccountRole;

import java.util.List;

@Data
@Entity
public class Manager extends Person{

   /* @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fio;

    //TODO: add validate
    @Email
    private String email;

    @Enumerated(value = EnumType.STRING)
    private AccountRole role= AccountRole.MANAGER;*/

    @OneToMany(mappedBy = "manager")
    private List<Employee> employees;
}
