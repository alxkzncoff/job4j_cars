package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс двигателя автомобиля.
 * @author Alkeksandr Kuznetsov.
 * @version 1.0
 */
@Entity
@Table(name = "engines")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Float volume;

    public Engine of(Float volume) {
        Engine engine = new Engine();
        engine.volume = volume;
        return engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Engine: id=" + id
                + ", volume=" + volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return id == engine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
