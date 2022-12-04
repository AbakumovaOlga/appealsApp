create sequence hibernate_sequence start 1 increment 1;

create table account (
                         id int8 not null,
                         password varchar(255) not null,
                         role varchar(255) not null,
                         username varchar(255) not null,
                         primary key (id)
);

create table appeal (
                        id int8 not null,
                        appeal_status varchar(255) not null,
                        appeal_type varchar(255) not null,
                        date timestamp not null,
                        employee_id int8 not null,
                        primary key (id)
);

create table employee (
                          id int8 not null,
                          email varchar(255) not null,
                          fio varchar(255),
                          username varchar(255) not null,
                          manager_id int8 not null,
                          primary key (id)
);

create table manager (
                         id int8 not null,
                         email varchar(255) not null,
                         fio varchar(255),
                         username varchar(255) not null,
                         primary key (id)
);

alter table if exists account
    add constraint UK_account_username unique (username);
     

alter table if exists employee
    add constraint UK_employee_username unique (username);
     

alter table if exists manager
    add constraint UK_manager_username unique (username);
     

alter table if exists appeal
    add constraint FK_appeal_employee
    foreign key (employee_id)
    references employee;
     

alter table if exists employee
    add constraint FK_employee_manager
    foreign key (manager_id)
    references manager;