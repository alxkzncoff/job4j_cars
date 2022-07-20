package ru.job4j.cars.persistence;

import net.jcip.annotations.ThreadSafe;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Drive;

import java.util.List;

@ThreadSafe
@Repository
public class DriveDBStore implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(DriveDBStore.class);
    private final SessionFactory sf;

    public DriveDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Метод добавляет тип привода в БД.
     * @param drive Привод.
     * @return Добавленный двигатель.
     */
    public Drive add(Drive drive) {
        tx(
                session -> session.save(drive),
                sf,
                LOG
        );
        return drive;
    }

    /**
     * Метод возвращает тип привода по id, если он есть в БД.
     * @param id Идентификационный номер типа привода.
     * @return Найденный тип привода.
     */
    public Drive findById(int id) {
        return tx(
                session -> (Drive) session.createQuery("FROM Drive WHERE id = :dId")
                        .setParameter("dId", id)
                        .uniqueResult(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает тип привода по названию, если он есть в БД.
     * @param name Название типа привода.
     * @return Найденный тип привода.
     */
    public Drive findByName(String name) {
        return tx(
                session -> (Drive) session.createQuery("FROM Drive WHERE name = :dName")
                        .setParameter("dName", name)
                        .uniqueResult(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает список всех типов привода из БД.
     * @return Список типов привода.
     */
    public List<Drive> findAll() {
        return tx(
                session -> session.createQuery("FROM Drive ").list(),
                sf,
                LOG
        );
    }

    /**
     * Метод удаляет тип привода из БД по id.
     * @param id Идентификационный номер типа привода.
     */
    public void delete(int id) {
        tx(
                session -> session.createQuery("DELETE FROM Drive WHERE id = :dId")
                        .setParameter("dId", id)
                        .executeUpdate(),
                sf,
                LOG
        );
    }
}
