package ru.abakumova.appealsapp.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@MappedSuperclass
@Data
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fio;

    @NotNull
    @Email
    private String email;

    @Column(unique = true)
    @NotNull
    private String username;

}
