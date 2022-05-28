create table comments
(
    id      bigserial
        constraint comment_pk
            primary key,
    comment text,
    date    date,
    user_id bigint
        constraint comment_users_id_fk
            references users,
    news_id bigint
        constraint comment_news_id_fk
            references news
);