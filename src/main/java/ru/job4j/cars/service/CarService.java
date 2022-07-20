package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.persistence.CarDBStore;

import java.util.List;

@ThreadSafe
@Service
public class CarService {
    private final CarDBStore store;

    public CarService(CarDBStore store) {
        this.store = store;
    }

    /**
     * Метод добавляет автомобиль в сервис.
     * @param car Автомобиль.
     * @return Добавленный автомобиль.
     */
    public Car add(Car car) {
        return store.add(car);
    }

    /**
     * Метод возвращает автомобиль по id, если он есть в сервисе.
     * @param id Идентификационный номер авто.
     * @return Найденное авто.
     */
    public Car findById(int id) {
        return store.findById(id);
    }

    /**
     * Метод возвращает список всех автомобилей.
     * @return Список авто.
     */
    public List<Car> findAll() {
        return store.findAll();
    }

    /**
     * Метод удаляет автомобиль из сервиса по id.
     * @param id Идентификационный номер авто.
     */
    public void delete(int id) {
        store.delete(id);
    }
}

