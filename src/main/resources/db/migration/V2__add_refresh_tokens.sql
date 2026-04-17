CREATE TABLE refresh_tokens (
    token TEXT PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    expiration TIMESTAMP WITH TIME ZONE NOT NULL default now()
);