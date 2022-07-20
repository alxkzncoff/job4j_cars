CREATE TABLE IF NOT EXISTS makes(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

INSERT INTO makes(name) values ('KIA');
INSERT INTO makes(name) values ('Hyundai');
INSERT INTO makes(name) values ('Volkswagen');

-- INSERT INTO makes(name, model_id) values ('KIA', 1);
-- INSERT INTO makes(name, model_id) values ('KIA', 2);
-- INSERT INTO makes(name, model_id) values ('KIA', 3);
-- INSERT INTO makes(name, model_id) values ('KIA', 4);
-- INSERT INTO makes(name, model_id) values ('KIA', 5);
-- INSERT INTO makes(name, model_id) values ('KIA', 6);
-- INSERT INTO makes(name, model_id) values ('KIA', 7);
-- INSERT INTO makes(name, model_id) values ('KIA', 8);
--
-- INSERT INTO makes(name, model_id) values ('Hyundai', 9);
-- INSERT INTO makes(name, model_id) values ('Hyundai', 10);
-- INSERT INTO makes(name, model_id) values ('Hyundai', 11);
-- INSERT INTO makes(name, model_id) values ('Hyundai', 12);
-- INSERT INTO makes(name, model_id) values ('Hyundai', 13);
-- INSERT INTO makes(name, model_id) values ('Hyundai', 14);
-- INSERT INTO makes(name, model_id) values ('Hyundai', 15);
-- INSERT INTO makes(name, model_id) values ('Hyundai', 16);
-- INSERT INTO makes(name, model_id) values ('Hyundai', 17);
-- INSERT INTO makes(name, model_id) values ('Hyundai', 18);
--
-- INSERT INTO makes(name, model_id) values ('Volkswagen', 19);
-- INSERT INTO makes(name, model_id) values ('Volkswagen', 20);
-- INSERT INTO makes(name, model_id) values ('Volkswagen', 21);
-- INSERT INTO makes(name, model_id) values ('Volkswagen', 22);
-- INSERT INTO makes(name, model_id) values ('Volkswagen', 23);
-- INSERT INTO makes(name, model_id) values ('Volkswagen', 24);
-- INSERT INTO makes(name, model_id) values ('Volkswagen', 25);
-- INSERT INTO makes(name, model_id) values ('Volkswagen', 26);
