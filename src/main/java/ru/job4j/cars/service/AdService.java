package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.persistence.AdDBStore;

import java.util.List;

@ThreadSafe
@Service
public class AdService {
    private final AdDBStore store;

    public AdService(AdDBStore store) {
        this.store = store;
    }

    /**
     * Метод добавляет объявление в сервис.
     * @param ad Объявление.
     * @return Добавленное объявление.
     */
    public Advertisement add(Advertisement ad) {
        return store.add(ad);
    }

    /**
     * Метод обновляет описание объявления в сервисе.
     * @param id Идентификационный номер объявления.
     * @param ad Обновленное объявление.
     */
    public void update(int id, Advertisement ad) {
        store.update(id, ad);
    }

    /**
     * Метод меняет статус объявления на "Продано".
     * @param id Идентификационный номер объявления.
     */
    public void makeSold(int id) {
        store.makeSold(id);
    }

    /**
     * Метод возвращает объявление по id, если оно есть в сервисе.
     * @param id Идентификационный номер объявления.
     * @return Найденное объявление.
     */
    public Advertisement findById(int id) {
        return store.findById(id);
    }

    /**
     * Метод возвращает список объявлений из сервиса определенной марки.
     * @return Список объявлений.
     */
    public List<Advertisement> findByMakeName(String name) {
        return store.findByMakeName(name);
    }

    /**
     * Метод возвращает список объявлений из сервиса за сегодняшний день.
     * @return Список объявлений.
     */
    public List<Advertisement> findToday() {
        return store.findToday();
    }

    /**
     * Метод возвращает список объявлений из сервиса у которых есть фотография.
     * @return Список объявлений.
     */
    public List<Advertisement> findByPhoto() {
        return store.findByPhoto();
    }

    /**
     * Метод возвращает список всех объявлений.
     * @return Список объявлений.
     */
    public List<Advertisement> findAll() {
        return store.findAll();
    }

    /**
     * Метод удаляет объявление из сервиса по id.
     * @param id Идентификационный номер объявления.
     */
    public void delete(int id) {
        store.delete(id);
    }
}
