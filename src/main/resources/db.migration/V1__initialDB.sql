drop table if exists events;
drop table if exists files;
drop table if exists users;

create table events
(
    id      bigint not null auto_increment,
    created datetime,
    updated datetime,
    file_id bigint,
    user_id bigint,
    primary key (id)
) engine = MyISAM;

create table files
(
    id        bigint       not null auto_increment,
    file_name varchar(100) not null,
    path      varchar(255),
    primary key (id)
) engine = MyISAM;

create table users
(
    id   bigint      not null auto_increment,
    name varchar(45) not null,
    primary key (id)
) engine = MyISAM;


alter table events
    add constraint FKav02cmkm91xjn1e1euy2w39ht
        foreign key (file_id)
            references files (id);

alter table events
    add constraint FKat8p3s7yjcp57lny4udqvqncq
        foreign key (user_id)
            references users (id);



create table users_events
(
    User_id   bigint not null,
    events_id bigint not null
) engine = MyISAM;

alter table users_events
    add constraint UK_21e1oy14mcx8h5pblcwt70ugi unique (events_id);

alter table users_events
    add constraint FK56qu3rya56tll071qq9emrfwn
        foreign key (events_id)
            references events (id);

alter table users_events
    add constraint FKl455m19j8pw620rwisctyfldt
        foreign key (User_id)
            references users (id)