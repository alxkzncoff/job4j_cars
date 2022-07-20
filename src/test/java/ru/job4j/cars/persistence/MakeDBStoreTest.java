package ru.job4j.cars.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import ru.job4j.cars.Main;
import ru.job4j.cars.model.Make;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MakeDBStoreTest {

    @Test(expected = ConstraintViolationException.class)
    public void whenAddDuplicate() {
        SessionFactory sf = new Main().sf();
        Make m1 = Make.of("make");
        Make m2 = Make.of("make");
        MakeDBStore store = new MakeDBStore(sf);
        store.add(m1);
        store.add(m2);
    }

    @Test
    public void whenFindById() {
        SessionFactory sf = new Main().sf();
        Make make = Make.of("make");
        MakeDBStore store = new MakeDBStore(sf);
        store.add(make);
        assertThat(store.findById(make.getId()), is(make));
    }

    @Test
    public void whenFindByName() {
        SessionFactory sf = new Main().sf();
        Make mk1 = Make.of("make 1");
        Make mk2 = Make.of("make 2");
        MakeDBStore store = new MakeDBStore(sf);
        store.add(mk1);
        store.add(mk2);
        assertThat(store.findByName(mk1.getName()), is(mk1));
    }

    @Test
    public void whenFindAll() {
        SessionFactory sf = new Main().sf();
        Make mk1 = Make.of("make 1");
        Make mk2 = Make.of("make 2");
        MakeDBStore store = new MakeDBStore(sf);
        store.add(mk1);
        store.add(mk2);
        assertThat(store.findAll().size(), is(2));
    }

    @Test
    public void whenDelete() {
        SessionFactory sf = new Main().sf();
        Make make = Make.of("make");
        MakeDBStore store = new MakeDBStore(sf);
        store.add(make);
        store.delete(make.getId());
        assertThat(store.findAll().size(), is(0));
    }
}