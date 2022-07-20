CREATE TABLE IF NOT EXISTS engines(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

INSERT INTO engines(name) VALUES ('Дизель');
INSERT INTO engines(name) VALUES ('Бензин');