package ru.job4j.cars.persistence;

import org.hibernate.SessionFactory;
import org.junit.Test;
import ru.job4j.cars.Main;
import ru.job4j.cars.model.Drive;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DriveDBStoreTest {

    @Test
    public void whenFindById() {
        SessionFactory sf = new Main().sf();
        Drive drive = Drive.of("drive");
        DriveDBStore store = new DriveDBStore(sf);
        store.add(drive);
        assertThat(store.findById(drive.getId()), is(drive));
    }

    @Test
    public void whenFindByName() {
        SessionFactory sf = new Main().sf();
        Drive drive = Drive.of("drive");
        DriveDBStore store = new DriveDBStore(sf);
        store.add(drive);
        assertThat(store.findByName(drive.getName()), is(drive));
    }

    @Test
    public void whenFindAll() {
        SessionFactory sf = new Main().sf();
        Drive d1 = Drive.of("engine 1");
        Drive d2 = Drive.of("engine 2");
        DriveDBStore store = new DriveDBStore(sf);
        store.add(d1);
        store.add(d2);
        assertThat(store.findAll().size(), is(2));
    }

    @Test
    public void whenDelete() {
        SessionFactory sf = new Main().sf();
        Drive drive = Drive.of("drive");
        DriveDBStore store = new DriveDBStore(sf);
        store.add(drive);
        store.delete(drive.getId());
        assertThat(store.findAll().size(), is(0));
    }

}