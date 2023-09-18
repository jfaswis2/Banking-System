package com.example.bankingsystem;

import com.example.bankingsystem.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EntityUnitTest {

    @Autowired
    private TestEntityManager testEntityManager;

    private User u1;

    @Test
    public void should_set_id_user() {
        u1 = new User();

        Long expectedId = 12345L;
        u1.setId(expectedId);

        Long actualId = u1.getId();

        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    public void should_save_a_user_with_setters() {
        u1 = new User();
        u1.setName("Jhon");
        u1.setLastName("Aristizabal");
        u1.setDni("23330241X");
        u1.setEmail("jfaswis2@gmail.com");
        u1.setPassword("password");
        u1.setDeleted(false);
        u1.setCreationDate(LocalDate.now());
        u1.setDateOfBirth(LocalDate.of(1997,3,2));


        testEntityManager.persistAndFlush(u1);
        User user = testEntityManager.find(User.class, u1.getId());
        checkData(u1,user);
    }

    @Test
    public void should_save_a_user_with_constructor() {
        u1 = new User(null,
                "Jhon",
                "Aristizabal",
                "jfaswis2@gmail.com",
                "11330134W",
                "password",
                LocalDate.of(1997,3,2),
                LocalDate.now(),
                false);

        testEntityManager.persistAndFlush(u1);
        User user = testEntityManager.find(User.class, u1.getId());

        checkData(u1,user);
    }

    @Test
    public void should_save_a_user_with_builder_pattern() {
        u1 = User.builder()
                .id(null)
                .name("Jhon")
                .lastName("Aristizabal")
                .email("jfaswis2@gmail.com")
                .dni("65370142W")
                .password("password")
                .dateOfBirth(LocalDate.of(1997,3,2))
                .creationDate(LocalDate.now())
                .deleted(false).build();

        testEntityManager.persistAndFlush(u1);
        User user = testEntityManager.find(User.class, u1.getId());

        checkData(u1,user);
    }

    @Test
    public void should_validate_user_dni() {
        u1 = new User();

        u1.setDni("23370321W");
        Assertions.assertTrue(u1.getDni()
                .matches("(\\d{8})(-?)([A-Z])"));
    }

    @Test
    public void should_not_validate_user_dni() {
        u1 = new User();

        u1.setDni("21472644T");
        Assertions.assertFalse("X15320234W"
                .matches("(\\d{8})(-?)([A-Z])"));
    }

    @Test
    public void should_validate_user_email() {
        u1 = new User();

        u1.setEmail("jfaswis2@gmail.com");
        Assertions.assertTrue(u1.getEmail()
                .matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"));
    }

    @Test
    public void should_not_validate_user_email() {
        u1 = new User();

        u1.setEmail("jfaswis2");
        Assertions.assertFalse(u1.getEmail()
                .matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"));
    }

    public void checkData(User u1, User u2) {
        assertThat(u1.getId()).isEqualTo(u2.getId());
        assertThat(u1.getName()).isEqualTo(u2.getName());
        assertThat(u1.getLastName()).isEqualTo(u2.getLastName());
        assertThat(u1.getDni()).isEqualTo(u2.getDni());
        assertThat(u1.getCreationDate()).isEqualTo(u2.getCreationDate());
        assertThat(u1.getPassword()).isEqualTo(u2.getPassword());
        assertThat(u1.getEmail()).isEqualTo(u2.getEmail());
        assertThat(u1.getDateOfBirth()).isEqualTo(u2.getDateOfBirth());
        assertThat(u1.isDeleted()).isEqualTo(u2.isDeleted());
    }
}
