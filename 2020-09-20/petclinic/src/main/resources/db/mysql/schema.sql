-- CREATE DATABASE IF NOT EXISTS petclinic;

-- ALTER DATABASE petclinic
--   DEFAULT CHARACTER SET utf8
--   DEFAULT COLLATE utf8_general_ci;

-- GRANT ALL PRIVILEGES ON petclinic.* TO user@localhost IDENTIFIED BY 'user';
USE petclinic;

create table if not exists roles
(
    id   bigint auto_increment
        primary key,
    name enum ('ADMIN', 'EDITOR', 'USER', 'ANONYMOUS') not null
) engine = MyISAM;

create table if not exists user_role
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
) engine = MyISAM;

create index if not exists FKt7e7djp752sqn6w22i6ocqy6q
    on user_role (role_id);

create table if not exists users
(
    id       bigint auto_increment
        primary key,
    password varchar(6)   null,
    username varchar(255) null,
    constraint UK_r43af9ap4edm43mmtq01oddj6
        unique (username)
) engine = MyISAM;


create table if not exists owners
(
    id         bigint auto_increment primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    address    varchar(255) null,
    city       varchar(255) null,
    phone      varchar(255) null,
    index(last_name)
) engine = MyISAM;

create table if not exists pets
(
    id         bigint auto_increment primary key,
    birth_date date                                               null,
    kind       enum ('CAT', 'DOG', 'FISH', 'REPTILES', 'UNKNOWN') not null,
    name       varchar(255)                                       null,
    owner_id   bigint                                             null
) engine = MyISAM;

create index if not exists FK6teg4kcjcnjhduguft56wcfoa
    on pets (owner_id);

create table if not exists visits
(
    id          bigint auto_increment
        primary key,
    description varchar(255) null,
    pet_id      bigint       not null,
    visit_date  date         null
) engine = MyISAM;

create index if not exists FK6jcifhlqqlsfseu67utlouauy
    on visits (pet_id);
