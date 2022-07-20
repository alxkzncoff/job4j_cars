package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.persistence.EngineDBStore;

import java.util.List;

@ThreadSafe
@Service
public class EngineService {
    private final EngineDBStore store;

    public EngineService(EngineDBStore store) {
        this.store = store;
    }

    /**
     * Метод добавляет тип двигателя в сервис.
     * @param engine Двигатель.
     * @return Добавленный двигатель.
     */
    public Engine add(Engine engine) {
        return store.add(engine);
    }

    /**
     * Метод возвращает тип двигателя по id, если он есть в сервисе.
     * @param id Идентификационный номер типа двигателя.
     * @return Найденный тип двигателя.
     */
    public Engine findById(int id) {
        return store.findById(id);
    }

    /**
     * Метод возвращает тип двигателя по названию, если он есть в сервисе.
     * @param name Название типа двигателя.
     * @return Найденный тип двигателя.
     */
    public Engine findByName(String name) {
        return store.findByName(name);
    }

    /**
     * Метод возвращает список всех типов двигателей из сервиса.
     * @return Список типов двигателей.
     */
    public List<Engine> findAll() {
        return store.findAll();
    }

    /**
     * Метод удаляет тип двигателя из сервиса по id.
     * @param id Идентификационный номер типа двигателя.
     */
    public void delete(int id) {
        store.delete(id);
    }
}
