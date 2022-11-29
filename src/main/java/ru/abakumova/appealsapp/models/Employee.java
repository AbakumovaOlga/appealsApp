package ru.abakumova.appealsapp.models;

import javax.persistence.*;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;
import ru.abakumova.appealsapp.models.enums.AccountRole;

import java.util.List;

@Data
@Entity
@JsonIncludeProperties(value = {"fio", "username"})
public class Employee extends Person {
   /* @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fio;

    //TODO: add validate
    @Email
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    private Account account;
*/
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @OneToMany(mappedBy = "employee")
    private List<Appeal> appeals;
}
