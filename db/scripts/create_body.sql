CREATE TABLE IF NOT EXISTS bodies(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

INSERT INTO bodies(name) VALUES ('Седан');
INSERT INTO bodies(name) VALUES ('Хэтчбек');
INSERT INTO bodies(name) VALUES ('Внедорожник');
