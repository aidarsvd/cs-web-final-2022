create table news_likes
(
    news_id bigint
        constraint news_likes_news_id_fk
            references news,
    user_id bigint
        constraint news_likes_users_id_fk
            references users
);