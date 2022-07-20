package ru.job4j.cars.persistence;

import org.hibernate.SessionFactory;
import org.junit.Test;
import ru.job4j.cars.Main;
import ru.job4j.cars.model.Transmission;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TransDBStoreTest {

    @Test
    public void whenFindById() {
        SessionFactory sf = new Main().sf();
        Transmission trans = Transmission.of("transmission");
        TransDBStore store = new TransDBStore(sf);
        store.add(trans);
        assertThat(store.findById(trans.getId()), is(trans));
    }

    @Test
    public void whenFindByName() {
        SessionFactory sf = new Main().sf();
        Transmission trans = Transmission.of("transmission");
        TransDBStore store = new TransDBStore(sf);
        store.add(trans);
        assertThat(store.findByName(trans.getName()), is(trans));
    }

    @Test
    public void whenFindAll() {
        SessionFactory sf = new Main().sf();
        Transmission t1 = Transmission.of("transmission 1");
        Transmission t2 = Transmission.of("transmission 2");
        TransDBStore store = new TransDBStore(sf);
        store.add(t1);
        store.add(t2);
        assertThat(store.findAll().size(), is(2));
    }

    @Test
    public void whenDelete() {
        SessionFactory sf = new Main().sf();
        Transmission trans = Transmission.of("transmission");
        TransDBStore store = new TransDBStore(sf);
        store.add(trans);
        store.delete(trans.getId());
        assertThat(store.findAll().size(), is(0));
    }


}