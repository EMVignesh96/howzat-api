CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(64) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) REFERENCES users(username),
    authority VARCHAR(50) NOT NULL,
    UNIQUE (username, authority)
);

CREATE TABLE user_keys (
    u_key VARCHAR(6),
    username VARCHAR(50) REFERENCES users(username),
    UNIQUE (username)
);
