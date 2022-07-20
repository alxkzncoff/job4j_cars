package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Make;
import ru.job4j.cars.persistence.MakeDBStore;

import java.util.List;

@ThreadSafe
@Service
public class MakeService {
    private final MakeDBStore store;

    public MakeService(MakeDBStore store) {
        this.store = store;
    }

    /**
     * Метод добавляет марку автомобиля в сервис.
     * @param make Марка автомобиля.
     * @return Добавленная марка автомобиля.
     */
    public Make add(Make make) {
        return store.add(make);
    }

    /**
     * Метод возвращает марку автомобиля по id, если она есть в сервисе.
     * @param id Идентификационный номер марки.
     * @return Найденная марка.
     */
    public Make findById(int id) {
        return store.findById(id);
    }

    /**
     * Метод возвращает список марок автомобилей по названию
     * со всеми моделями, если они есть в сервисе.
     * @param name Название марки.
     * @return Список марок.
     */
    public Make findByName(String name) {
        return store.findByName(name);
    }

    /**
     * Метод возвращает список всех марок автомобиля со всеми моделями из сервиса.
     * @return Список марок.
     */
    public List<Make> findAll() {
        return store.findAll();
    }

    /**
     * Метод удаляет марку автомобиля из сервиса по id.
     * @param id Идентификационный номер марки.
     */
    public void delete(int id) {
        store.delete(id);
    }
}
