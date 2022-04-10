INSERT INTO files_storage_servlet.files (file_name, path)
VALUES ('hibernate.doc', 'c:\java'),
       ('flyway.doc', 'c:\java\course'),
       ('heroku.doc', 'c:\course\db'),
       ('account.xls', 'c:\english\db'),
       ('ticket.doc', 'c:\java\db');

insert into files_storage_servlet.users (name)
values ('Jevelin'),
       ('Bayraktar'),
       ('Peter'),
       ('Svetlana'),
       ('Nikolai'),
       ('Sergei'),
       ('Oleg');

insert into files_storage_servlet.events (created, updated, file_id, user_id)
values ('2022-03-08 12:19:03.000000', '2022-03-08 12:19:06.000000', 1, 1),
       ('2022-03-12 12:19:03.000000', '2022-03-12 12:19:06.000000', 2, 2),
       ('2022-03-10 12:10:03.000000', '2022-03-10 12:19:06.000000', 3, 1),
       ('2022-03-11 12:10:03.000000', '2022-03-12:19:06.000000', 2, 3);