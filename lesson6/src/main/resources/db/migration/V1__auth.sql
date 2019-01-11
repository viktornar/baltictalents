create table users
(
  id bigint not null auto_increment primary key,
  password varchar(255) null,
  username varchar(255) null
);

create table roles
(
  id bigint not null auto_increment primary key,
  name enum ('ADMIN', 'EDITOR', 'READER', 'ANONYMOUS') not null
);

create table user_role
(
  user_id bigint not null,
  role_id bigint not null,
  primary key (user_id, role_id),
  constraint FK_user_role_users
    foreign key (user_id) references users (id),
  constraint FK_user_role_roles
    foreign key (role_id) references roles (id)
);