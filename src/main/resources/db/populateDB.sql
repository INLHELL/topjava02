DELETE FROM USER_ROLES;
DELETE FROM USERS;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

-- admin
INSERT INTO USERS (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');
-- password
INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO USER_ROLES (role, user_id) VALUES ('ADMIN', 100000);
INSERT INTO USER_ROLES (role, user_id) VALUES ('USER', 100001);

INSERT INTO MEALS (date, description, calories, user_id) VALUES (TIMESTAMP '2015-01-06 09:00', 'breakfast', 500,
                                                                 100001);
INSERT INTO MEALS (date, description, calories, user_id) VALUES (TIMESTAMP '2015-01-06 13:00', 'dinner', 1000,
                                                                 100001);
INSERT INTO MEALS (date, description, calories, user_id) VALUES (TIMESTAMP '2015-01-07 00:00', 'supper', 600, 100001);
INSERT INTO MEALS (date, description, calories, user_id) VALUES (TIMESTAMP '2015-01-07 13:00', 'dinner', 1300,
                                                                 100001);
INSERT INTO MEALS (date, description, calories, user_id) VALUES (TIMESTAMP '2015-01-06 14:00', 'admin_meal', 2000,
                                                                 100000);
