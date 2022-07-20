package ru.job4j.cars.persistence;

import net.jcip.annotations.ThreadSafe;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Model;

import java.util.List;

@ThreadSafe
@Repository
public class ModelDBStore implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(ModelDBStore.class);
    private final SessionFactory sf;

    public ModelDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Метод добавляет модель автомобиля в БД.
     * @param model Модель автомобиля.
     * @return Добавленная модель автомобиля.
     */
    public Model add(Model model) {
        tx(
                session -> session.save(model),
                sf,
                LOG
        );
        return model;
    }

    /**
     * Метод возвращает модель автомобиля по id, если она есть в БД.
     * @param id Идентификационный номер модели.
     * @return Найденная модель.
     */
    public Model findById(int id) {
        return tx(
                session -> (Model) session.createQuery("SELECT DISTINCT m FROM Model m "
                                + "JOIN FETCH m.make WHERE m.id = :mId")
                        .setParameter("mId", id)
                        .uniqueResult(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает список моделей автомобиля по названию из БД.
     * @param name Название модели.
     * @return Список моделей.
     */
    public Model findByName(String name) {
        return tx(
                session -> (Model) session.createQuery("SELECT DISTINCT m FROM Model m "
                                + "JOIN FETCH m.make WHERE m.name = :mName")
                        .setParameter("mName", name)
                        .uniqueResult(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает список моделей автомобиля по названию марки из БД.
     * @param name Название марки.
     * @return Список моделей.
     */
    public List<Model> findByMakeName(String name) {
        return tx(
                session -> session.createQuery("SELECT DISTINCT m FROM Model m "
                                + "JOIN FETCH m.make mk WHERE mk.name = :mkName")
                        .setParameter("mkName", name)
                        .list(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает список всех моделей автомобиля из БД.
     * @return Список моделей.
     */
    public List<Model> findAll() {
        return tx(
                session -> session.createQuery("SELECT DISTINCT m FROM Model m "
                        + "JOIN FETCH m.make ").list(),
                sf,
                LOG
        );
    }

    /**
     * Метод удаляет модель автомобиля из БД по id.
     * @param id Идентификационный номер модели.
     */
    public void delete(int id) {
        tx(
                session -> session.createQuery("DELETE FROM Model WHERE id = :mId")
                        .setParameter("mId", id)
                        .executeUpdate(),
                sf,
                LOG
        );
    }
}
