drop table if exists product;
create table if not exists product
(
    id          bigint          not null auto_increment,
    name        varchar(255)    not null,
    barcode     varchar(255),
    photo_url   varchar(255),
    cost        decimal         not null,
    price       decimal         not null,
    created_at  timestamp       not null,
    updated_at  timestamp       not null,
    created_by  timestamp,
    updated_by  timestamp,
    primary key (id)
);
