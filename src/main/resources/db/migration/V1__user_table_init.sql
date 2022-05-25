create table users
(
    id       bigserial
        constraint users_pk
            primary key,
    name     varchar(256),
    surname  varchar(256),
    username varchar(256),
    email    varchar(256),
    avatar   varchar(256),
    password varchar(256)
);

create table roles
(
    id   bigserial
        constraint roles_pk
            primary key,
    name varchar(256)
);

create table user_roles
(
    user_id bigint
        constraint user_roles_users_id_fk
            references users (id),
    role_id bigint
        constraint user_roles__roles_fk
            references roles (id)
);