CREATE TABLE account (
username VARCHAR(20) UNIQUE,
password CHAR(64),
granted_authorities TEXT,
is_account_non_expired BOOLEAN,
is_account_non_locked BOOLEAN,
is_credentials_non_expired BOOLEAN,
is_enabled BOOLEAN,
user_key CHAR(6)
)