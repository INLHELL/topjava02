DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- admin
INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');
-- password
INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO user_roles (role, user_id) VALUES ('ADMIN', 100000);
INSERT INTO user_roles (role, user_id) VALUES ('USER',  100001);
