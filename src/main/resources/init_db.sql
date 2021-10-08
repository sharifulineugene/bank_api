--liquibase formatted sql
--changeset sharifulin:init_db splitStatements:true endDelimiter:;

create table PERSON
(
    ID            INT auto_increment,
    NAME          VARCHAR(255) not null,
    SURNAME       VARCHAR(255) not null,
    DATE_OF_BIRTH CHAR(10)     not null
);

INSERT INTO PERSON(NAME,SURNAME,DATE_OF_BIRTH) VALUES('Ivan','Petrov','13.07.1995'),('Masha','Sidorova','27.07.1990'),
                                                      ('Petr','Grishko','04.04.1980');

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

INSERT INTO ACCOUNT(NUMBER,PERSON_ID,BALANCE) VALUES
 ('00000000000000000001',1,1)
,('00000000000000000002',1,2)
,('00000000000000000003',1,3)
,('00000000000000000004',2,1)
,('00000000000000000005',2,2)
,('00000000000000000006',2,3)
,('00000000000000000007',3,1)
,('00000000000000000008',3,2)
,('00000000000000000009',3,3);


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

INSERT INTO CARD(CARD_NUMBER, EXP_DATE, ACCOUNT_ID, STATUS) VALUES
 ('0000000000000001','10/24',1,'ACTIVE')
,('0000000000000002','10/24',1,'NEW')
,('0000000000000003','10/24',1,'BLOCKED')
,('0000000000000004','10/24',2,'ACTIVE')
,('0000000000000005','10/24',2,'NEW')
,('0000000000000006','10/24',2,'BLOCKED')
,('0000000000000007','10/24',3,'ACTIVE')
,('0000000000000008','10/24',3,'NEW')
,('0000000000000009','10/24',3,'BLOCKED')
,('0000000000000010','10/24',4,'ACTIVE')
,('0000000000000011','10/24',4,'NEW')
,('0000000000000012','10/24',4,'BLOCKED')
,('0000000000000013','10/24',5,'ACTIVE')
,('0000000000000014','10/24',5,'NEW')
,('0000000000000015','10/24',5,'BLOCKED')
,('0000000000000016','10/24',6,'ACTIVE')
,('0000000000000017','10/24',6,'NEW')
,('0000000000000018','10/24',6,'BLOCKED')
,('0000000000000019','10/24',7,'ACTIVE')
,('0000000000000020','10/24',7,'NEW')
,('0000000000000021','10/24',7,'BLOCKED')
,('0000000000000022','10/24',8,'ACTIVE')
,('0000000000000023','10/24',8,'NEW')
,('0000000000000024','10/24',8,'BLOCKED')
,('0000000000000025','10/24',9,'ACTIVE')
,('0000000000000026','10/24',9,'NEW')
,('0000000000000027','10/24',9,'BLOCKED');
