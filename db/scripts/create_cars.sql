CREATE TABLE IF NOT EXISTS cars(
    id SERIAL PRIMARY KEY,
    name varchar(255),
    description TEXT,
    body varchar(50),
    photo BYTEA,
    engine_id INT NOT NULL REFERENCES engines(id)
);