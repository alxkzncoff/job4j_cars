CREATE TABLE IF NOT EXISTS cars(
    id SERIAL PRIMARY KEY,
    make_id INT NOT NULL REFERENCES makes(id),
    model_id INT NOT NULL REFERENCES models(id),
    engine_id INT NOT NULL REFERENCES engines(id),
    body_id INT NOT NULL REFERENCES bodies(id),
    drive_id INT NOT NULL REFERENCES drives(id),
    transmission_id INT NOT NULL REFERENCES transmissions(id)
);