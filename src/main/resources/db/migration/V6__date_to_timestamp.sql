alter table news
    drop column date;

alter table news
    add date timestamp;

alter table comments
    drop column date;

alter table comments
    add date timestamp;

