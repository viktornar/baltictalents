-- CREATE DATABASE IF NOT EXISTS petclinic;

-- ALTER DATABASE petclinic
--   DEFAULT CHARACTER SET utf8
--   DEFAULT COLLATE utf8_general_ci;

-- GRANT ALL PRIVILEGES ON petclinic.* TO user@localhost IDENTIFIED BY 'user';
USE petclinic;

CREATE TABLE IF NOT EXISTS owners (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  address VARCHAR(255),
  city VARCHAR(80),
  telephone VARCHAR(20),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS pets (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  birth_date DATE,
  owner_id INT(4) UNSIGNED NOT NULL,
  INDEX(name),
  FOREIGN KEY (owner_id) REFERENCES owners(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS visits (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  pet_id INT(4) UNSIGNED NOT NULL,
  visit_date DATE,
  description VARCHAR(255),
  FOREIGN KEY (pet_id) REFERENCES pets(id)
) engine=InnoDB;

create table if not exists users
(
  id bigint not null auto_increment primary key,
  username varchar(255) null,
  password varchar(255) null
);

create table if not exists roles
(
  id bigint not null auto_increment primary key,
  name enum ('ADMIN', 'EDITOR', 'USER', 'ANONYMOUS') not null
);

create table if not exists user_role
(
  user_id bigint not null,
  role_id bigint not null,
  primary key (user_id, role_id),
  constraint FK_user_role_users
    foreign key (user_id) references users (id),
  constraint FK_user_role_roles
    foreign key (role_id) references roles (id)
);
