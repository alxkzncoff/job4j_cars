CREATE TABLE IF NOT EXISTS transmissions(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

INSERT INTO transmissions(name) VALUES ('Механическая');
INSERT INTO transmissions(name) VALUES ('Автоматическая');
INSERT INTO transmissions(name) VALUES ('Вариатор');
INSERT INTO transmissions(name) VALUES ('Робот');