package ru.job4j.cars.persistence;

import net.jcip.annotations.ThreadSafe;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.List;

@ThreadSafe
@Repository
public class EngineDBStore implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(AdDBStore.class);
    private final SessionFactory sf;

    public EngineDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Метод добавляет тип двигателя в БД.
     * @param engine Двигатель.
     * @return Добавленный двигатель.
     */
    public Engine add(Engine engine) {
        tx(
                session -> session.save(engine),
                sf,
                LOG
        );
        return engine;
    }

    /**
     * Метод возвращает тип двигателя по id, если он есть в БД.
     * @param id Идентификационный номер типа двигателя.
     * @return Найденный тип двигателя.
     */
    public Engine findById(int id) {
        return tx(
                session -> (Engine) session.createQuery("FROM Engine WHERE id = :eId")
                        .setParameter("eId", id)
                        .uniqueResult(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает тип двигателя по названию, если он есть в БД.
     * @param name Название типа двигателя.
     * @return Найденный тип двигателя.
     */
    public Engine findByName(String name) {
        return tx(
                session -> (Engine) session.createQuery("FROM Engine WHERE name = :eName")
                        .setParameter("eName", name)
                        .uniqueResult(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает список всех типов двигателей из БД.
     * @return Список типов двигателей.
     */
    public List<Engine> findAll() {
        return tx(
                session -> session.createQuery("FROM Engine ").list(),
                sf,
                LOG
        );
    }

    /**
     * Метод удаляет тип двигателя из БД по id.
     * @param id Идентификационный номер типа двигателя.
     */
    public void delete(int id) {
        tx(
                session -> session.createQuery("DELETE FROM Engine WHERE id = :eId")
                        .setParameter("eId", id)
                        .executeUpdate(),
                sf,
                LOG
        );
    }
}
