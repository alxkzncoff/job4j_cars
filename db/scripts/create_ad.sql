CREATE TABLE IF NOT EXISTS advertisements(
    id SERIAL PRIMARY KEY,
    description VARCHAR(255),
    sold BOOLEAN,
    created TIMESTAMP,
    photo BYTEA,
    car_id INT NOT NULL REFERENCES cars(id),
    user_id INT NOT NULL REFERENCES users(id)
);