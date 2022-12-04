package ru.abakumova.appealsapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;

import java.util.List;

@Data
@Entity
@JsonIncludeProperties(value = {"fio", "username"})
public class Employee extends Person {
    @ManyToOne
    @JoinColumn(name = "manager_id")
    @NotNull
    private Manager manager;

    @OneToMany(mappedBy = "employee")
    private List<Appeal> appeals;
}
