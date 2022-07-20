package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Transmission;
import ru.job4j.cars.persistence.TransDBStore;

import java.util.List;

@ThreadSafe
@Service
public class TransService {
    private final TransDBStore store;

    public TransService(TransDBStore store) {
        this.store = store;
    }

    /**
     * Метод добавляет тип трансмиссии в сервис.
     * @param trans Трансмиссия.
     * @return Добавленная трансмиссия.
     */
    public Transmission add(Transmission trans) {
        return store.add(trans);
    }

    /**
     * Метод возвращает тип трансмиссии по id, если она есть в сервисе.
     * @param id Идентификационный номер трансмиссии.
     * @return Найденная трансмиссия.
     */
    public Transmission findById(int id) {
        return store.findById(id);
    }

    /**
     * Метод возвращает тип трансмиссии по названию, если она есть в сервисе.
     * @param name Название трансмиссии.
     * @return Найденная трансмиссия.
     */
    public Transmission findByName(String name) {
        return store.findByName(name);
    }

    /**
     * Метод возвращает список всех типов трансмиссий из сервиса.
     * @return Список трансмиссий.
     */
    public List<Transmission> findAll() {
        return store.findAll();
    }

    /**
     * Метод удаляет тип трансмиссии из сервиса по id.
     * @param id Идентификационный номер трансмиссии.
     */
    public void delete(int id) {
        store.delete(id);
    }
}
