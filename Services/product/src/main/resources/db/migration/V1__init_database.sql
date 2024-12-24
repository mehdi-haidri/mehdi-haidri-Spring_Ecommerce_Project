create table if not exists category
(
    id          integer not null
    primary key,
    description varchar(255),
    name        varchar(255)
    );

create table if not exists product
(
    id                 integer          not null
    primary key,
    quantity double precision not null,
    description        varchar(255),
    name               varchar(255),
    price              numeric(38, 2),
    image               varchar(255),
    category_id        integer
    constraint fk1_foreinkey1
    references category
    );

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;