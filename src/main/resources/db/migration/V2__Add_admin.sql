insert into usr (id, username, password, active)
    values (1, 'admin', '$2a$08$7xsA1wCC8T376eks5CS40u7Xtit4lcjzR97ajqrKd9VQnX7I./lhW', true);

insert into user_role (user_id, roles)
values (1, 'USER'), (1, 'ADMIN');