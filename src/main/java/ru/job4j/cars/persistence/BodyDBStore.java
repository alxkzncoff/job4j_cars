package ru.job4j.cars.persistence;

import net.jcip.annotations.ThreadSafe;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Body;

import java.util.List;

@ThreadSafe
@Repository
public class BodyDBStore implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(BodyDBStore.class);
    private final SessionFactory sf;

    public BodyDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Метод добавляет тип кузова в БД.
     * @param body Тип кузова.
     * @return Добавленный тип кузова.
     */
    public Body add(Body body) {
        tx(
                session -> session.save(body),
                sf,
                LOG
        );
        return body;
    }

    /**
     * Метод возвращает тип кузова по id, если он есть в БД.
     * @param id Идентификационный номер типа кузова.
     * @return Найденный тип кузова.
     */
    public Body findById(int id) {
        return tx(
                session -> (Body) session.createQuery("FROM Body WHERE id = :bId")
                        .setParameter("bId", id)
                        .uniqueResult(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает тип кузова по названию, если он есть в БД.
     * @param name Название типа кузова.
     * @return Найденный тип кузова.
     */
    public Body findByName(String name) {
        return tx(
                session -> (Body) session.createQuery("FROM Body WHERE name = :bName")
                        .setParameter("bName", name)
                        .uniqueResult(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает список всех типов кузова из БД.
     * @return Список типов кузова.
     */
    public List<Body> findAll() {
        return tx(
                session -> session.createQuery("FROM Body ").list(),
                sf,
                LOG
        );
    }

    /**
     * Метод удаляет тип кузова из БД по id.
     * @param id Идентификационный номер типа кузова.
     */
    public void delete(int id) {
        tx(
                session -> session.createQuery("DELETE FROM Body WHERE id = :bId")
                        .setParameter("bId", id)
                        .executeUpdate(),
                sf,
                LOG
        );
    }
}
