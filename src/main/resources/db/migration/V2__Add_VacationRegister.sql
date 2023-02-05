create table vacation_register (
                                   id int8 not null,
                                   vacation_count int4 not null,
                                   date timestamp,
                                   employee_id int8,
                                   primary key (id)
);

alter table if exists vacation_register
    add constraint FK_vacation_register_employee
        foreign key (employee_id)
            references employee;