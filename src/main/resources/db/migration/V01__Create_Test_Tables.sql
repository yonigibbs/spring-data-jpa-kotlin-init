CREATE TABLE user
(
    user_name VARCHAR(50) NOT NULL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

INSERT INTO user VALUES ('joe.bloggs', 'Joe', 'Bloggs')