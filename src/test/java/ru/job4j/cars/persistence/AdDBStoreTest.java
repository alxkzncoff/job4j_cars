package ru.job4j.cars.persistence;

import org.hibernate.SessionFactory;
import org.junit.Test;
import ru.job4j.cars.Main;
import ru.job4j.cars.model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AdDBStoreTest {

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
        CarDBStore carDBStore = new CarDBStore(sf);
        carDBStore.add(car);
        Advertisement ad = Advertisement.of("description", "price",
                "mileage", "color", car);
        AdDBStore store = new AdDBStore(sf);
        store.add(ad);
        assertThat(store.findById(ad.getId()), is(ad));
    }

    @Test
    public void whenMakeSold() {
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
        CarDBStore carDBStore = new CarDBStore(sf);
        carDBStore.add(car);
        Advertisement ad = Advertisement.of("description", "price",
                "mileage", "color", car);
        AdDBStore store = new AdDBStore(sf);
        store.add(ad);
        store.makeSold(ad.getId());
        assertTrue(store.findById(ad.getId()).getSold());
    }

    @Test
    public void whenUpdate() {
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
        CarDBStore carDBStore = new CarDBStore(sf);
        carDBStore.add(car);
        Advertisement ad1 = Advertisement.of("description 1", "price 1",
                "mileage 1", "color 1", car);
        Advertisement ad2 = Advertisement.of("description 2", "price 2",
                "mileage 2", "color 2", car);
        AdDBStore store = new AdDBStore(sf);
        store.add(ad1);
        store.update(ad1.getId(), ad2);
        assertThat(store.findById(ad1.getId()).getDescription(), is(ad2.getDescription()));
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
        CarDBStore carDBStore = new CarDBStore(sf);
        carDBStore.add(c1);
        carDBStore.add(c2);
        Advertisement ad1 = Advertisement.of("description 1", "price 1",
                "mileage 1", "color 1", c1);
        Advertisement ad2 = Advertisement.of("description 2", "price 2",
                "mileage 2", "color 2", c2);
        AdDBStore store = new AdDBStore(sf);
        store.add(ad1);
        store.add(ad2);
        assertThat(store.findByMakeName(mk2.getName()).get(0), is(ad2));
    }

    @Test
    public void whenFindByToday() {
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
        CarDBStore carDBStore = new CarDBStore(sf);
        carDBStore.add(c1);
        carDBStore.add(c2);
        Advertisement ad1 = Advertisement.of("description 1", "price 1",
                "mileage 1", "color 1", c1);
        Advertisement ad2 = Advertisement.of("description 2", "price 2",
                "mileage 2", "color 2", c2);
        LocalDateTime daysAgo = LocalDateTime.now().minusDays(2);
        ad2.setCreated(Timestamp.valueOf(daysAgo));
        AdDBStore store = new AdDBStore(sf);
        store.add(ad1);
        store.add(ad2);
        assertThat(store.findToday().get(0), is(ad1));
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
        CarDBStore carDBStore = new CarDBStore(sf);
        carDBStore.add(c1);
        carDBStore.add(c2);
        Advertisement ad1 = Advertisement.of("description 1", "price 1",
                "mileage 1", "color 1", c1);
        Advertisement ad2 = Advertisement.of("description 2", "price 2",
                "mileage 2", "color 2", c2);
        AdDBStore store = new AdDBStore(sf);
        store.add(ad1);
        store.add(ad2);
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
        CarDBStore carDBStore = new CarDBStore(sf);
        carDBStore.add(car);
        Advertisement ad = Advertisement.of("description", "price",
                "mileage", "color", car);
        AdDBStore store = new AdDBStore(sf);
        store.add(ad);
        store.delete(ad.getId());
        assertThat(store.findAll().size(), is(0));
    }

}