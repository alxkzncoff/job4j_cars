package ru.job4j.cars.persistence;

import net.jcip.annotations.ThreadSafe;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Transmission;

import java.util.List;

@ThreadSafe
@Repository
public class TransDBStore implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(TransDBStore.class);
    private final SessionFactory sf;

    public TransDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Метод добавляет тип трансмиссии в БД.
     * @param trans Трансмиссия.
     * @return Добавленная трансмиссия.
     */
    public Transmission add(Transmission trans) {
        tx(
                session -> session.save(trans),
                sf,
                LOG
        );
        return trans;
    }

    /**
     * Метод возвращает тип трансмиссии по id, если она есть в БД.
     * @param id Идентификационный номер трансмиссии.
     * @return Найденная трансмиссия.
     */
    public Transmission findById(int id) {
        return tx(
                session -> (Transmission) session.createQuery("FROM Transmission WHERE id = :tId")
                        .setParameter("tId", id)
                        .uniqueResult(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает тип трансмиссии по названию, если она есть в БД.
     * @param name Название трансмиссии.
     * @return Найденная трансмиссия.
     */
    public Transmission findByName(String name) {
        return tx(
                session -> (Transmission) session.createQuery("FROM Transmission WHERE name = :tName")
                        .setParameter("tName", name)
                        .uniqueResult(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает список всех типов трансмиссий из БД.
     * @return Список трансмиссий.
     */
    public List<Transmission> findAll() {
        return tx(
                session -> session.createQuery("FROM Transmission ").list(),
                sf,
                LOG
        );
    }

    /**
     * Метод удаляет тип трансмиссии из БД по id.
     * @param id Идентификационный номер трансмиссии.
     */
    public void delete(int id) {
        tx(
                session -> session.createQuery("DELETE FROM Transmission WHERE id = :tId")
                        .setParameter("tId", id)
                        .executeUpdate(),
                sf,
                LOG
        );
    }
}
