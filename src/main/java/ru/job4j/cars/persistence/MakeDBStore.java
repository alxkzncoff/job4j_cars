package ru.job4j.cars.persistence;

import net.jcip.annotations.ThreadSafe;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Make;

import java.util.List;

@ThreadSafe
@Repository
public class MakeDBStore implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(MakeDBStore.class);
    private final SessionFactory sf;

    public MakeDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Метод добавляет марку автомобиля в БД.
     * @param make Марка автомобиля.
     * @return Добавленная марка автомобиля.
     */
    public Make add(Make make) {
        tx(
                session -> session.save(make),
                sf,
                LOG
        );
        return make;
    }

    /**
     * Метод возвращает марку автомобиля по id, если она есть в БД.
     * @param id Идентификационный номер марки.
     * @return Найденная марка.
     */
    public Make findById(int id) {
        return tx(
                session -> (Make) session.createQuery("FROM Make WHERE id = :mId")
                        .setParameter("mId", id)
                        .uniqueResult(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает список марок автомобилей по названию
     * со всеми моделями, если они есть в БД.
     * @param name Название марки.
     * @return Список марок.
     */
    public Make findByName(String name) {
        return tx(
                session -> (Make) session.createQuery("FROM Make WHERE name = :mName")
                        .setParameter("mName", name)
                        .uniqueResult(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает список всех марок автомобиля со всеми моделями из БД.
     * @return Список марок.
     */
    public List<Make> findAll() {
        return tx(
                session -> session.createQuery("FROM Make ").list(),
                sf,
                LOG
        );
    }

    /**
     * Метод удаляет марку автомобиля из БД по id.
     * @param id Идентификационный номер марки.
     */
    public void delete(int id) {
        tx(
                session -> session.createQuery("DELETE FROM Make WHERE id = :mId")
                        .setParameter("mId", id)
                        .executeUpdate(),
                sf,
                LOG
        );
    }
}
