package ru.job4j.cars.persistence;

import org.hibernate.SessionFactory;
import org.junit.Test;
import ru.job4j.cars.Main;
import ru.job4j.cars.model.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CarDBStoreTest {

    @Test
    public void whenFindById() {
        SessionFactory sf = new Main().sf();
        Make make = Make.of("make");
        MakeDBStore makeDBStore = new MakeDBStore(sf);
        makeDBStore.add(make);
        Model model = Model.of("model", make);
        ModelDBStore modelDBStore = new ModelDBStore(sf);
        modelDBStore.add(model);
        Body body = Body.of("body");
        BodyDBStore bodyDBStore = new BodyDBStore(sf);
        bodyDBStore.add(body);
        Engine engine = Engine.of("engine");
        EngineDBStore engineDBStore = new EngineDBStore(sf);
        engineDBStore.add(engine);
        Drive drive = Drive.of("drive");
        DriveDBStore driveDBStore = new DriveDBStore(sf);
        driveDBStore.add(drive);
        Transmission trans = Transmission.of("transmission");
        TransDBStore transDBStore = new TransDBStore(sf);
        transDBStore.add(trans);
        Car car = Car.of(make, model, engine, body, drive, trans);
        CarDBStore store = new CarDBStore(sf);
        store.add(car);
        assertThat(store.findById(car.getId()), is(car));
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
        ModelDBStore modelDBStore = new ModelDBStore(sf);
        modelDBStore.add(m1);
        modelDBStore.add(m2);
        Body b1 = Body.of("body 1");
        Body b2 = Body.of("body 2");
        BodyDBStore bodyDBStore = new BodyDBStore(sf);
        bodyDBStore.add(b1);
        bodyDBStore.add(b2);
        Engine e1 = Engine.of("engine 1");
        Engine e2 = Engine.of("engine 2");
        EngineDBStore engineDBStore = new EngineDBStore(sf);
        engineDBStore.add(e1);
        engineDBStore.add(e2);
        Drive d1 = Drive.of("drive 1");
        Drive d2 = Drive.of("drive 2");
        DriveDBStore driveDBStore = new DriveDBStore(sf);
        driveDBStore.add(d1);
        driveDBStore.add(d2);
        Transmission t1 = Transmission.of("transmission 1");
        Transmission t2 = Transmission.of("transmission 1");
        TransDBStore transDBStore = new TransDBStore(sf);
        transDBStore.add(t1);
        transDBStore.add(t2);
        Car c1 = Car.of(mk1, m1, e1, b1, d1, t1);
        Car c2 = Car.of(mk2, m2, e2, b2, d2, t2);
        CarDBStore store = new CarDBStore(sf);
        store.add(c1);
        store.add(c2);
        assertThat(store.findAll().size(), is(2));
    }

    @Test
    public void whenDelete() {
        SessionFactory sf = new Main().sf();
        Make make = Make.of("make");
        MakeDBStore makeDBStore = new MakeDBStore(sf);
        makeDBStore.add(make);
        Model model = Model.of("model", make);
        ModelDBStore modelDBStore = new ModelDBStore(sf);
        modelDBStore.add(model);
        Body body = Body.of("body");
        BodyDBStore bodyDBStore = new BodyDBStore(sf);
        bodyDBStore.add(body);
        Engine engine = Engine.of("engine");
        EngineDBStore engineDBStore = new EngineDBStore(sf);
        engineDBStore.add(engine);
        Drive drive = Drive.of("drive");
        DriveDBStore driveDBStore = new DriveDBStore(sf);
        driveDBStore.add(drive);
        Transmission trans = Transmission.of("transmission");
        TransDBStore transDBStore = new TransDBStore(sf);
        transDBStore.add(trans);
        Car car = Car.of(make, model, engine, body, drive, trans);
        CarDBStore store = new CarDBStore(sf);
        store.add(car);
        store.delete(make.getId());
        assertThat(store.findAll().size(), is(0));
    }

}