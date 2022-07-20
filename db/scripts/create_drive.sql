CREATE TABLE IF NOT EXISTS drives(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

INSERT INTO drives(name) VALUES ('Передний');
INSERT INTO drives(name) VALUES ('Задний');
INSERT INTO drives(name) VALUES ('Полный');