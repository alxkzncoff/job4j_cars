CREATE TABLE IF NOT EXISTS models(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)UNIQUE,
    make_id INT NOT NULL REFERENCES makes(id)
);

INSERT INTO models(name, make_id) VALUES ('Rio', 1);
INSERT INTO models(name, make_id) VALUES ('Ceed', 1);
INSERT INTO models(name, make_id) VALUES ('Sportage', 1);

INSERT INTO models(name, make_id) VALUES ('Solaris', 2);
INSERT INTO models(name, make_id) VALUES ('Creta', 2);
INSERT INTO models(name, make_id) VALUES ('i30', 2);

INSERT INTO models(name, make_id) VALUES ('Polo', 3);
INSERT INTO models(name, make_id) VALUES ('Golf', 3);
INSERT INTO models(name, make_id) VALUES ('Tiguan', 3);

-- INSERT INTO models(name, body_id, engine_id) VALUES ('Rio', 1, 1);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Rio', 1, 2);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Rio', 2, 1);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Rio', 2, 2);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Ceed', 2, 1);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Ceed', 2, 2);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Sportage', 3, 1);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Sportage', 3, 2);
--
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Solaris', 1, 1);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Solaris', 1, 2);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Solaris', 2, 1);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Solaris', 2, 2);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Creta', 3, 1);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Creta', 3, 2);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('i30', 1, 1);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('i30', 1, 2);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('i30', 2, 1);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('i30', 2, 2);
--
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Polo', 1, 1);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Polo', 1, 2);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Polo', 2, 1);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Polo', 2, 2);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Golf', 2, 1);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Golf', 2, 2);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Tiguan', 3, 1);
-- INSERT INTO models(name, body_id, engine_id) VALUES ('Tiguan', 3, 2);