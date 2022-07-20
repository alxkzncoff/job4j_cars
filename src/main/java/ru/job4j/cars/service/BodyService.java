package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Body;
import ru.job4j.cars.persistence.BodyDBStore;

import java.util.List;

@ThreadSafe
@Service
public class BodyService {
    private final BodyDBStore store;

    public BodyService(BodyDBStore store) {
        this.store = store;
    }

    /**
     * Метод добавляет тип кузова в сервис.
     * @param body Тип кузова.
     * @return Добавленный тип кузова.
     */
    public Body add(Body body) {
        return store.add(body);
    }

    /**
     * Метод возвращает тип кузова из сервиса по id.
     * @param id Идентификационный номер типа кузова.
     * @return Найденный тип кузова.
     */
    public Body findById(int id) {
        return store.findById(id);
    }

    /**
     * Метод возвращает тип кузова по названию из сервиса.
     * @param name Название типа кузова.
     * @return Найденный тип кузова.
     */
    public Body findByName(String name) {
        return store.findByName(name);
    }

    /**
     * Метод возвращает список всех типов кузова из сервиса.
     * @return Список типов кузова.
     */
    public List<Body> findAll() {
        return store.findAll();
    }

    /**
     * Метод удаляет тип кузова из сервиса по id.
     * @param id Идентификационный номер типа кузова.
     */
    public void delete(int id) {
        store.delete(id);
    }
}
