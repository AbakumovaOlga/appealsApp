package ru.abakumova.appealsapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.abakumova.appealsapp.models.Appeal;
import ru.abakumova.appealsapp.models.Manager;
import ru.abakumova.appealsapp.models.enums.AppealStatus;
import ru.abakumova.appealsapp.models.enums.AppealType;

import java.util.List;

@Repository
public interface AppealRepository extends JpaRepository<Appeal, Long> {

    Appeal findAppealById(Long id);

    @Query(value = "select a from Appeal a  join Employee e on e.id=a.employee.id where e.manager = ?1 and a.appealStatus=?2")
    List<Appeal> getAppealsByManagerAndAppealStatus(Manager manager, AppealStatus appealStatus);
}
