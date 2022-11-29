package ru.abakumova.appealsapp.models;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Data
@Entity
public class Manager extends Person {
    @OneToMany(mappedBy = "manager")
    private List<Employee> employees;
}
