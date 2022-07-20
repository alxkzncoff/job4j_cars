package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Drive;
import ru.job4j.cars.persistence.DriveDBStore;

import java.util.List;

@ThreadSafe
@Service
public class DriveService {
    private final DriveDBStore store;

    public DriveService(DriveDBStore store) {
        this.store = store;
    }

    /**
     * Метод добавляет тип привода в сервис.
     * @param drive Привод.
     * @return Добавленный двигатель.
     */
    public Drive add(Drive drive) {
        return store.add(drive);
    }

    /**
     * Метод возвращает тип привода по id, если он есть в сервисе.
     * @param id Идентификационный номер типа привода.
     * @return Найденный тип привода.
     */
    public Drive findById(int id) {
        return store.findById(id);
    }

    /**
     * Метод возвращает тип привода по названию, если он есть в сервисе.
     * @param name Название типа привода.
     * @return Найденный тип привода.
     */
    public Drive findByName(String name) {
        return store.findByName(name);
    }

    /**
     * Метод возвращает список всех типов привода из сервиса.
     * @return Список типов привода.
     */
    public List<Drive> findAll() {
        return store.findAll();
    }

    /**
     * Метод удаляет тип привода из сервиса по id.
     * @param id Идентификационный номер типа привода.
     */
    public void delete(int id) {
        store.delete(id);
    }
}
