CREATE TABLE IF NOT EXISTS cars(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    body VARCHAR(50),
    photo BYTEA,
    engine_id INT NOT NULL REFERENCES engines(id)
);