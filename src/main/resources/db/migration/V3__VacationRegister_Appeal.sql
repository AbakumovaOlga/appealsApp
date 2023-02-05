alter table if exists appeal
    add column vacation_register_id int8;


alter table if exists appeal
    add constraint FK_appeal_vacationRegister
        foreign key (vacation_register_id)
            references vacation_register