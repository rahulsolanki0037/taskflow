CREATE TABLE refresh_token (
    id UUID PRIMARY KEY,
    token VARCHAR(255),
    expiry_date TIMESTAMP,
    user_id UUID REFERENCES users(id)
);