CREATE TABLE IF NOT EXISTS history_owners(
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id),
    car_id INT NOT NULL REFERENCES cars(id)
);