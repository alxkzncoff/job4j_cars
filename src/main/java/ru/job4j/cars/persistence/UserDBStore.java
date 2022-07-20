package ru.job4j.cars.persistence;

import net.jcip.annotations.ThreadSafe;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import java.util.Optional;

@ThreadSafe
@Repository
public class UserDBStore implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(UserDBStore.class);
    private final SessionFactory sf;

    public UserDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * Метод добавляет пользователя в БД.
     * @param user Пользователь.
     */
    public void add(User user) {
        tx(
                session -> session.save(user),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает пользователя по id, если он есть в БД.
     * @param id Идентификационный номер пользователя.
     * @return Найденный пользователь.
     */
    public Optional<User> findById(int id) {
        return tx(
                session -> session.createQuery("FROM User WHERE id = :uId")
                        .setParameter("uId", id)
                        .uniqueResultOptional(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает пользователя из БД по почте.
     * @param email Почта пользователя.
     * @return Найденный пользователь или Optional.empty().
     */
    public Optional<User> findByEmail(String email) {
        return tx(
                session -> session.createQuery("FROM User WHERE email = :uEmail")
                        .setParameter("uEmail", email)
                        .uniqueResultOptional(),
                sf,
                LOG
        );
    }

    /**
     * Метод возвращает пользователя из БД по почте и паролю.
     * @param email Почта пользователя.
     * @param password Пароль пользователя.
     * @return Найденный пользователь или Optional.empty().
     */
    public Optional<User> findByEmailAndPwd(String email, String password) {
        return tx(
                session -> session.createQuery("FROM User WHERE email = :uEmail and password = :uPassword")
                        .setParameter("uEmail", email)
                        .setParameter("uPassword", password)
                        .uniqueResultOptional(),
                sf,
                LOG
        );
    }
}
