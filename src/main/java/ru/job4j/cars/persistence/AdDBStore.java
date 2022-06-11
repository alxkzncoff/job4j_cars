package ru.job4j.cars.persistence;

import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Advertisement;

import java.util.List;
import java.util.function.Function;

@ThreadSafe
@Repository
public class AdDBStore {
    private static final Logger LOG = LoggerFactory.getLogger(AdDBStore.class);
    private final SessionFactory sf;

    public AdDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            LOG.error("HQL Exception", e);
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * Метод возвращает список объявлений из БД определенной марки.
     * @return Список объявлений.
     */
    public List<Advertisement> findByCarName(String carName) {
        return this.tx(
                session -> session.createQuery("select distinct a from Advertisement a "
                        + "join fetch a.car c "
                        + "join fetch a.user u "
                        + "join fetch c.engine e "
                        + "join fetch c.drivers where c.name = :cName")
                        .setParameter("cName", carName)
                        .list()
        );
    }

    /**
     * Метод возвращает список объявлений из БД за сегодняшний день.
     * @return Список объявлений.
     */
    public List<Advertisement> findToday() {
        return this.tx(
                session -> session.createQuery("select distinct a from Advertisement a "
                        + "join fetch a.car c "
                        + "join fetch a.user u "
                        + "join fetch c.engine e "
                        + "join fetch c.drivers where a.created > CURRENT_DATE").list()
        );
    }

    /**
     * Метод возвращает список объявлений из БД у которых есть фотография.
     * @return Список объявлений.
     */
    public List<Advertisement> findByPhoto() {
        return this.tx(
                session -> session.createQuery("select distinct a from Advertisement a "
                        + "join fetch a.car c "
                        + "join fetch a.user u "
                        + "join fetch c.engine e "
                        + "join fetch c.drivers where a.photo.size > 0").list()
        );
    }
}
