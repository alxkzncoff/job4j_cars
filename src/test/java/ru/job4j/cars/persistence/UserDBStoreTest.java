package ru.job4j.cars.persistence;

import org.junit.Test;
import ru.job4j.cars.Main;
import ru.job4j.cars.model.User;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserDBStoreTest {

    @Test
    public void whenFindById() {
        User expected = User.of("user", "123456789", "user@email.com", "password");
        UserDBStore store = new UserDBStore(new Main().sf());
        store.add(expected);
        Optional<User> actual = store.findById(1);
        assertThat(actual.get(), is(expected));
    }

    @Test
    public void whenFindByEmail() {
        User expected = User.of("user", "123456789", "user@email.com", "password");
        UserDBStore store = new UserDBStore(new Main().sf());
        store.add(expected);
        Optional<User> actual = store.findByEmail(expected.getEmail());
        assertThat(actual.get(), is(expected));
    }

    @Test
    public void whenFindByEmailAndPwd() {
        User expected = User.of("user", "123456789", "user@email.com", "password");
        UserDBStore store = new UserDBStore(new Main().sf());
        store.add(expected);
        Optional<User> actual = store.findByEmailAndPwd(expected.getEmail(), expected.getPassword());
        assertThat(actual.get(), is(expected));
    }
}