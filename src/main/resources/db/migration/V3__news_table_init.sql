create table news
(
    id      bigserial
        constraint news_pk
            primary key,
    title   text,
    content text,
    date    date
);