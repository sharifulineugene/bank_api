--liquibase formatted sql
--changeset sharifulin:init_db splitStatements:true endDelimiter:;

create table PERSON
(
    ID            INT auto_increment,
    NAME          VARCHAR(255) not null,
    SURNAME       VARCHAR(255) not null,
    DATE_OF_BIRTH CHAR(10)     not null
);

create unique index PERSON_ID_UINDEX
    on PERSON (ID);

alter table PERSON
    add constraint PERSON_PK
        primary key (ID);



create table ACCOUNT
(
    ID        INT auto_increment,
    NUMBER    VARCHAR(255)   not null,
    PERSON_ID INT,
    BALANCE   LONG default 0 not null,
    constraint ACCOUNT_PERSON_ID_FK
        foreign key (PERSON_ID) references PERSON (ID)
);

create unique index ACCOUNT_NUMBER_UINDEX
    on ACCOUNT (NUMBER);

create unique index ACCOUNT_ID_UINDEX
    on ACCOUNT (ID);

alter table ACCOUNT
    add constraint ACCOUNT_PK
        primary key (ID);


create table CARD
(
    ID          INT auto_increment,
    CARD_NUMBER CHAR(16)     not null,
    EXP_DATE    CHAR(5)      not null,
    ACCOUNT_ID  INT,
    STATUS      VARCHAR(255) not null,
    constraint CARD_ACCOUNT_ID_FK
        foreign key (ACCOUNT_ID) references ACCOUNT (ID)
);

create unique index CARD_CARD_NUMBER_UINDEX
    on CARD (CARD_NUMBER);

create unique index CARD_ID_UINDEX
    on CARD (ID);

alter table CARD
    add constraint CARD_PK
        primary key (ID);


