package ru.job4j.cars.persistence;

import org.hibernate.SessionFactory;
import org.junit.Test;
import ru.job4j.cars.Main;
import ru.job4j.cars.model.Engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EngineDBStoreTest {

    @Test
    public void whenFindById() {
        SessionFactory sf = new Main().sf();
        Engine engine = Engine.of("engine");
        EngineDBStore store = new EngineDBStore(sf);
        store.add(engine);
        assertThat(store.findById(engine.getId()), is(engine));
    }

    @Test
    public void whenFindByName() {
        SessionFactory sf = new Main().sf();
        Engine engine = Engine.of("engine");
        EngineDBStore store = new EngineDBStore(sf);
        store.add(engine);
        assertThat(store.findByName(engine.getName()), is(engine));
    }

    @Test
    public void whenFindAll() {
        SessionFactory sf = new Main().sf();
        Engine e1 = Engine.of("engine 1");
        Engine e2 = Engine.of("engine 2");
        EngineDBStore store = new EngineDBStore(sf);
        store.add(e1);
        store.add(e2);
        assertThat(store.findAll().size(), is(2));
    }

    @Test
    public void whenDelete() {
        SessionFactory sf = new Main().sf();
        Engine engine = Engine.of("engine");
        EngineDBStore store = new EngineDBStore(sf);
        store.add(engine);
        store.delete(engine.getId());
        assertThat(store.findAll().size(), is(0));
    }

}