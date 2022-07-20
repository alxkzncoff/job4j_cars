package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс модель автомобиля.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */
@Entity
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "make_id", foreignKey = @ForeignKey(name = "MAKE_ID_FK"))
    private Make make;

    public static Model of(String name, Make make) {
        Model m = new Model();
        m.name = name;
        m.make = make;
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

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    @Override
    public String toString() {
        return "Model: "
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
        Model model = (Model) o;
        return id == model.id && Objects.equals(name, model.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
