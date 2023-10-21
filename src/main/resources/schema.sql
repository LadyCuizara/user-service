DROP table IF EXISTS phone CASCADE;
DROP table IF EXISTS users CASCADE;
DROP table IF EXISTS user_session CASCADE;

DROP sequence IF EXISTS hibernate_sequence;
CREATE sequence hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE phone (
    id bigint not null,
    city_code integer,
    country_code integer,
    number integer,
    user_id binary(255),
    primary key (id)
);

CREATE TABLE users (
    id binary(255) not null,
    created timestamp not null,
    email varchar(255) not null,
    modified timestamp not null,
    name varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
);

ALTER TABLE phone ADD CONSTRAINT FKik7a2etdorybvoolvchfcvgkx FOREIGN KEY (user_id) REFERENCES users;

CREATE TABLE user_session (
    user_id binary(255) not null,
    is_active boolean,
    last_login timestamp,
    token varchar(255),
    primary key (user_id)
);
