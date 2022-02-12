create table if not exists Todo (
    id identity,
    name varchar(100) not null,
    description varchar(255) not null,
    createDate timestamp not null
);