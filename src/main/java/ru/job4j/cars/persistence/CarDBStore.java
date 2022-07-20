package ru.job4j.cars.persistence;

import net.jcip.annotations.ThreadSafe;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.List;

@ThreadSafe
@Repository
public class CarDBStore implements Store {
    private final static Logger LOG = LoggerFactory.getLogger(CarDBStore.class);
    private final SessionFactory sf;

    public CarDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Метод добавляет автомобиль в БД.
     * @param car Автомобиль.
     * @return Добавленный автомобиль.
     */
    public Car add(Car car) {
        tx(
                session -> session.save(car),
                sf,
                LOG
        );
        return car;
    }

    /**
     * Метод возвращает автомобиль по id, если он есть в БД.
     * @param id Идентификационный номер авто.
     * @return Найденное авто.
     */
    public Car findById(int id) {
        return tx(
                session -> (Car) session.createQuery("SELECT DISTINCT c FROM Car c "
                                + "JOIN FETCH c.model m "
                                + "JOIN FETCH m.make "
                                + "JOIN FETCH c.body "
                                + "JOIN FETCH c.engine WHERE c.id = :cId")
                        .setParameter("cId", id)
                        .uniqueResult(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает список всех автомобилей.
     * @return Список авто.
     */
    public List<Car> findAll() {
        return tx(
                session -> session.createQuery("SELECT DISTINCT c FROM Car c "
                        + "JOIN FETCH c.model m "
                        + "JOIN FETCH m.make "
                        + "JOIN FETCH c.body "
                        + "JOIN FETCH c.engine ").list(),
                sf,
                LOG
        );
    }

    /**
     * Метод удаляет автомобиль из БД по id.
     * @param id Идентификационный номер авто.
     */
    public void delete(int id) {
        tx(
                session -> session.createQuery("DELETE FROM Car WHERE id = :cId")
                        .setParameter("cId", id)
                        .executeUpdate(),
                sf,
                LOG
        );
    }
}
