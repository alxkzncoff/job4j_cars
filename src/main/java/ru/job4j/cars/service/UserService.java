package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;
import ru.job4j.cars.persistence.UserDBStore;

import java.util.Optional;

@ThreadSafe
@Repository
public class UserService {
    private final UserDBStore store;

    public UserService(UserDBStore store) {
        this.store = store;
    }

    /**
     * Метод добавляет пользователя в сервис.
     * @param user Пользователь.
     */
    public void add(User user) {
        store.add(user);
    }

    /**
     * Метод возвращает пользователя по id, если он есть в сервисе.
     * @param id Идентификационный номер пользователя.
     * @return Найденный пользователь.
     */
    public Optional<User> findById(int id) {
        return store.findById(id);
    }

    /**
     * Метод возвращает пользователя по почте, если он есть в сервисе.
     * @param email Почта пользователя.
     * @return Найденный пользователь или Optional.empty().
     */
    public Optional<User> findByEmail(String email) {
        return store.findByEmail(email);
    }

    /**
     * Метод возвращает пользователя по почте и паролю, если он есть в сервисе.
     * @param email Почта пользователя.
     * @param password Пароль пользователя.
     * @return Найденный пользователь или Optional.empty().
     */
    public Optional<User> findByEmailAndPwd(String email, String password) {
        return store.findByEmailAndPwd(email, password);
    }
}
