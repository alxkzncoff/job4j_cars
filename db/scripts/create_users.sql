CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    phone VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(50)
);

INSERT INTO users(name, phone, email, password) VALUES ('User 1', '123', 'user1', '123');
INSERT INTO users(name, phone, email, password) VALUES ('User 2', '456', 'user2', '123');
INSERT INTO users(name, phone, email, password) VALUES ('User 3', '789', 'user3', '123');