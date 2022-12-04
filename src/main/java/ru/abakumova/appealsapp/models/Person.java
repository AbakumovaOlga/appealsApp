package ru.abakumova.appealsapp.models;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;


@MappedSuperclass
@Data
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fio;

    //TODO: add validate
    @Email
    private String email;

    @Column(unique = true)
    private String username;

}
