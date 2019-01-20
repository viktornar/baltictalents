create table products
(
  id bigint not null auto_increment primary key,
  name varchar(255) null,
  description text null,
  price float(6,2) not null
);

create table images
(
  id bigint not null auto_increment primary key,
  name varchar(255) null,
  url varchar(512) null
);

create table product_image
(
  product_id bigint not null,
  image_id bigint not null,
  primary key (product_id, image_id),
  constraint FK_product_image_products
    foreign key (product_id) references products (id),
  constraint FK_product_image_images
    foreign key (image_id) references images (id)
);