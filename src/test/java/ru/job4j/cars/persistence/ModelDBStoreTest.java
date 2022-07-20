package ru.job4j.cars.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import ru.job4j.cars.Main;
import ru.job4j.cars.model.Make;
import ru.job4j.cars.model.Model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ModelDBStoreTest {

    @Test(expected = ConstraintViolationException.class)
    public void whenAddDuplicate() {
        SessionFactory sf = new Main().sf();
        MakeDBStore makeDBStore = new MakeDBStore(sf);
        Make make = Make.of("make");
        makeDBStore.add(make);
        Model m1 = Model.of("model", make);
        Model m2 = Model.of("model", make);
        ModelDBStore store = new ModelDBStore(sf);
        store.add(m1);
        store.add(m2);
    }

    @Test
    public void whenFindById() {
        SessionFactory sf = new Main().sf();
        MakeDBStore makeDBStore = new MakeDBStore(sf);
        Make make = Make.of("make");
        makeDBStore.add(make);
        Model model = Model.of("model", make);
        ModelDBStore store = new ModelDBStore(sf);
        store.add(model);
        assertThat(store.findById(model.getId()), is(model));
    }

    @Test
    public void whenFindAllByName() {
        SessionFactory sf = new Main().sf();
        Make mk1 = Make.of("make 1");
        Make mk2 = Make.of("make 2");
        MakeDBStore makeDBStore = new MakeDBStore(sf);
        makeDBStore.add(mk1);
        makeDBStore.add(mk2);
        Model m1 = Model.of("model 1", mk1);
        Model m2 = Model.of("model 2", mk2);
        ModelDBStore store = new ModelDBStore(sf);
        store.add(m1);
        store.add(m2);
        assertThat(store.findByName(m1.getName()), is(m1));
    }

    @Test
    public void whenFindByMakeName() {
        SessionFactory sf = new Main().sf();
        Make mk1 = Make.of("make 1");
        Make mk2 = Make.of("make 2");
        MakeDBStore makeDBStore = new MakeDBStore(sf);
        makeDBStore.add(mk1);
        makeDBStore.add(mk2);
        Model m1 = Model.of("model 1", mk1);
        Model m2 = Model.of("model 2", mk2);
        ModelDBStore store = new ModelDBStore(sf);
        store.add(m1);
        store.add(m2);
        assertThat(store.findByMakeName(mk1.getName()).get(0), is(m1));
        assertThat(store.findByMakeName(mk1.getName()).size(), is(1));
    }

    @Test
    public void whenFindAll() {
        SessionFactory sf = new Main().sf();
        Make mk1 = Make.of("make 1");
        Make mk2 = Make.of("make 2");
        MakeDBStore makeDBStore = new MakeDBStore(sf);
        makeDBStore.add(mk1);
        makeDBStore.add(mk2);
        Model m1 = Model.of("model 1", mk1);
        Model m2 = Model.of("model 2", mk2);
        ModelDBStore store = new ModelDBStore(sf);
        store.add(m1);
        store.add(m2);
        assertThat(store.findAll().size(), is(2));
    }

    @Test
    public void whenDelete() {
        SessionFactory sf = new Main().sf();
        MakeDBStore makeDBStore = new MakeDBStore(sf);
        Make make = Make.of("make");
        makeDBStore.add(make);
        Model model = Model.of("model", make);
        ModelDBStore store = new ModelDBStore(sf);
        store.add(model);
        store.delete(model.getId());
        assertThat(store.findAll().size(), is(0));
    }
}