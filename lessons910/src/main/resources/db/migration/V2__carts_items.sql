create table carts (
  id bigint not null auto_increment primary key
);
 
CREATE TABLE items (
  id bigint not null auto_increment primary key,
  cart_id bigint not null,
  name varchar(255) not null,
  description text null,
  price float(6,2) not null,
  constraint FK_items_cart
    foreign key (cart_id) references carts (id)
);