package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс марка автомобиля.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */
@Entity
@Table(name = "makes")
public class Make {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;

    public static Make of(String name) {
        Make m = new Make();
        m.name = name;
        return m;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Make: "
                + "id=" + id
                + ", name=" + name + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Make make = (Make) o;
        return id == make.id && Objects.equals(name, make.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
