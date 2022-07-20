package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Model;
import ru.job4j.cars.persistence.ModelDBStore;

import java.util.List;

@ThreadSafe
@Service
public class ModelService {
    private final ModelDBStore store;

    public ModelService(ModelDBStore store) {
        this.store = store;
    }

    /**
     * Метод добавляет модель автомобиля в сервис.
     * @param model Модель автомобиля.
     * @return Добавленная модель автомобиля.
     */
    public Model add(Model model) {
        return store.add(model);
    }

    /**
     * Метод возвращает модель автомобиля по id, если она есть в сервисе.
     * @param id Идентификационный номер модели.
     * @return Найденная модель.
     */
    public Model findById(int id) {
        return store.findById(id);
    }

    /**
     * Метод возвращает список моделей автомобиля по названию, если они есть в сервисе.
     * @param name Название модели.
     * @return Список моделей.
     */
    public Model findByName(String name) {
        return store.findByName(name);
    }

    /**
     * Метод возвращает список моделей автомобиля по названию марки из сервиса.
     * @param name Название марки.
     * @return Список моделей.
     */
    public List<Model> findByMakeName(String name) {
        return store.findByMakeName(name);
    }

    /**
     * Метод возвращает список всех моделей автомобиля из сервиса.
     * @return Список моделей.
     */
    public List<Model> findAll() {
        return store.findAll();
    }

    /**
     * Метод удаляет модель автомобиля из сервиса по id.
     * @param id Идентификационный номер модели.
     */
    public void delete(int id) {
        store.delete(id);
    }
}
