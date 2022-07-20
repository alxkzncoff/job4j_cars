package ru.job4j.cars.persistence;

import org.hibernate.SessionFactory;
import org.junit.Test;
import ru.job4j.cars.Main;
import ru.job4j.cars.model.Body;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BodyDBStoreTest {

    @Test
    public void whenFindById() {
        SessionFactory sf = new Main().sf();
        Body body = Body.of("body");
        BodyDBStore store = new BodyDBStore(sf);
        store.add(body);
        assertThat(store.findById(body.getId()), is(body));
    }

    @Test
    public void whenFindByName() {
        SessionFactory sf = new Main().sf();
        Body body = Body.of("body");
        BodyDBStore store = new BodyDBStore(sf);
        store.add(body);
        assertThat(store.findByName(body.getName()), is(body));
    }

    @Test
    public void whenFindAll() {
        SessionFactory sf = new Main().sf();
        Body b1 = Body.of("body 1");
        Body b2 = Body.of("body 2");
        BodyDBStore store = new BodyDBStore(sf);
        store.add(b1);
        store.add(b2);
        assertThat(store.findAll().size(), is(2));
    }

    @Test
    public void whenDelete() {
        SessionFactory sf = new Main().sf();
        Body body = Body.of("body");
        BodyDBStore store = new BodyDBStore(sf);
        store.add(body);
        store.delete(body.getId());
        assertThat(store.findAll().size(), is(0));
    }

}