package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс привод автомобиля.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */
@Entity
@Table(name = "drives")
public class Drive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public static Drive of(String name) {
        Drive drive = new Drive();
        drive.name = name;
        return drive;
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
        return "Drive: "
                + "id=" + id
                + ", name=" + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Drive drive = (Drive) o;
        return id == drive.id && Objects.equals(name, drive.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
