package ru.job4j.cars.persistence;

import net.jcip.annotations.ThreadSafe;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Advertisement;

import java.util.List;

@ThreadSafe
@Repository
public class AdDBStore implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(AdDBStore.class);
    private final SessionFactory sf;

    public AdDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Метод добавляет объявление в БД.
     * @param ad Объявление.
     * @return Добавленное объявление.
     */
    public Advertisement add(Advertisement ad) {
        tx(
                session -> session.save(ad),
                sf,
                LOG
        );
        return ad;
    }

    /**
     * Метод меняет статус объявления на "Продано".
     * @param id Идентификационный номер объявления.
     */
    public void makeSold(int id) {
        tx(
                session -> session.createQuery("UPDATE Advertisement a "
                                + "SET a.sold = :newSold WHERE a.id = :aId")
                        .setParameter("newSold", true)
                        .setParameter("aId", id)
                        .executeUpdate(),
                sf,
                LOG
        );
    }

    /**
     * Метод обновляет описание объявления в БД.
     * @param id Идентификационный номер объявления.
     * @param ad Обновленное объявление.
     */
    public void update(int id, Advertisement ad) {
        tx(
                session -> session.createQuery("UPDATE Advertisement a "
                        + "SET a.description = :adDesc, a.price = :adPrice WHERE a.id = :adId")
                        .setParameter("adDesc", ad.getDescription())
                        .setParameter("adPrice", ad.getPrice())
                        .setParameter("adId", id)
                        .executeUpdate(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает объявление по id, если оно есть в БД.
     * @param id Идентификационный номер объявления.
     * @return Найденное объявление.
     */
    public Advertisement findById(int id) {
        return tx(
                session -> (Advertisement) session.createQuery("SELECT DISTINCT a FROM Advertisement a "
                                + "JOIN FETCH a.car c "
                                + "JOIN FETCH c.model m "
                                + "JOIN FETCH m.make "
                                + "JOIN FETCH c.body "
                                + "JOIN FETCH c.drive "
                                + "JOIN FETCH c.engine WHERE a.id = :aId")
                        .setParameter("aId", id)
                        .uniqueResult(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает список объявлений из БД определенной марки.
     * @return Список объявлений.
     */
    public List<Advertisement> findByMakeName(String name) {
        return tx(
                session -> session.createQuery("SELECT DISTINCT a FROM Advertisement a "
                                + "JOIN FETCH a.car c "
                                + "JOIN FETCH c.model m "
                                + "JOIN FETCH m.make mk "
                                + "JOIN FETCH c.body "
                                + "JOIN FETCH c.drive "
                                + "JOIN FETCH c.engine WHERE mk.name = :mkName")
                        .setParameter("mkName", name)
                        .list(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает список объявлений из БД за сегодняшний день.
     * @return Список объявлений.
     */
    public List<Advertisement> findToday() {
        return tx(
                session -> session.createQuery("SELECT DISTINCT a FROM Advertisement a "
                        + "JOIN FETCH a.car c "
                        + "JOIN FETCH c.model m "
                        + "JOIN FETCH m.make "
                        + "JOIN FETCH c.body "
                        + "JOIN FETCH c.drive "
                        + "JOIN FETCH c.engine WHERE a.created > CURRENT_DATE").list(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает список объявлений из БД у которых есть фотография.
     * @return Список объявлений.
     */
    public List<Advertisement> findByPhoto() {
        return tx(
                session -> session.createQuery("SELECT DISTINCT a FROM Advertisement a "
                        + "JOIN FETCH a.car c "
                        + "JOIN FETCH c.model m "
                        + "JOIN FETCH m.make "
                        + "JOIN FETCH c.body "
                        + "JOIN FETCH c.drive "
                        + "JOIN FETCH c.engine WHERE a.photo != null ").list(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает список всех объявлений.
     * @return Список объявлений.
     */
    public List<Advertisement> findAll() {
        return tx(
                session -> session.createQuery("SELECT DISTINCT a FROM Advertisement a "
                        + "JOIN FETCH a.car c "
                        + "JOIN FETCH c.model m "
                        + "JOIN FETCH m.make "
                        + "JOIN FETCH c.body "
                        + "JOIN FETCH c.drive "
                        + "JOIN FETCH c.engine ").list(),
                sf,
                LOG
        );
    }

    /**
     * Метод удаляет объявление из БД по id.
     * @param id Идентификационный номер объявления.
     */
    public void delete(int id) {
        tx(
                session -> session.createQuery("DELETE FROM Advertisement WHERE id = :aId")
                        .setParameter("aId", id)
                        .executeUpdate(),
                sf,
                LOG
        );
    }
}
