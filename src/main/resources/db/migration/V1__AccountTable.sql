CREATE TABLE account (
username VARCHAR(20) UNIQUE,
password_hash CHAR(64),
user_key CHAR(6)
)