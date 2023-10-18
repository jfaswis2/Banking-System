package com.example.bankingsystem;

import com.example.bankingsystem.entities.Checking;
import com.example.bankingsystem.entities.Saving;
import com.example.bankingsystem.entities.User;
import com.example.bankingsystem.enums.Role;
import com.example.bankingsystem.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EntityUnitTest {

    @Autowired
    private TestEntityManager testEntityManager;

    private User u1;

    private Checking c1;
    private Saving s1;

    List<Checking> checkings;
    List<Saving> savings;
    @BeforeEach
    void setUp() {
        checkings = new ArrayList<>();
        savings = new ArrayList<>();

        c1 = new Checking();
        c1.setMinimumBalance(BigDecimal.valueOf(1000));
        c1.setMonthlyMaintenanceFee(BigDecimal.valueOf(12));
        c1.setBalance(BigDecimal.valueOf(0L));
        c1.setDeleted(false);
        c1.setCreationDate(LocalDate.now());
        c1.setStatus(Status.ACTIVE);
        c1.setPenaltyFee(BigDecimal.valueOf(40L));

        s1 = new Saving();
        s1.setInterestRate(BigDecimal.valueOf(0.4));
        s1.setBalance(BigDecimal.valueOf(2500));
        s1.setMinimumBalance(BigDecimal.valueOf(1000));
        s1.setPenaltyFee(BigDecimal.valueOf(40));
        s1.setDeleted(false);
        s1.setCreationDate(LocalDate.now());
        s1.setStatus(Status.ACTIVE);
    }

//---------------------------------- SAVING -------------------------------
    @Test
    public void should_save_a_saving_account_with_setters() {
    testEntityManager.persistAndFlush(s1);
    Saving saving = testEntityManager.find(Saving.class, s1.getId());
    checkSavingData(s1, saving);
    }

    @Test
    public void should_save_a_saving_account_with_constructor() {
        u1 = new User(null,
                "Jhon",
                "Aristizabal",
                "jfaswis25@gmail.com",
                "11231184W",
                "password",
                LocalDate.of(1997, 3, 2),
                LocalDate.now(),
                false,
                Role.USER,
                checkings,
                savings);
        testEntityManager.persistAndFlush(u1);

        Saving saving = new Saving(BigDecimal.valueOf(0L),
                BigDecimal.valueOf(40L),
                BigDecimal.valueOf(1000),
                LocalDate.now(),
                Status.ACTIVE,
                false,
                BigDecimal.valueOf(12),
                u1);

        u1.getSavings().add(saving);

        testEntityManager.persistAndFlush(saving);

        Saving savingResponse = testEntityManager.find(Saving.class, saving.getId());
        checkSavingData(saving, savingResponse);
    }

    @Test
    public void should_set_id_saving() {
        Saving saving = new Saving();

        Long expectedId = 12345L;
        saving.setId(expectedId);

        Long actualId = saving.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void should_set_user_in_saving() {
        u1 = new User(null,
                "Jhon",
                "Aristizabal",
                "jfaswis25@gmail.com",
                "11231184W",
                "password",
                LocalDate.of(1997, 3, 2),
                LocalDate.now(),
                false,
                Role.USER,
                checkings,
                savings);

        testEntityManager.persistAndFlush(u1);

        s1.setUser(u1);

        testEntityManager.persistAndFlush(s1);

        Saving saving = testEntityManager.find(Saving.class, s1.getId());

        assertEquals(saving.getUser(), u1);
    }

//---------------------------------- CHECKING -----------------------------------

    @Test
    public void should_save_a_checking_account_with_setters() {
            testEntityManager.persistAndFlush(c1);
            Checking checking = testEntityManager.find(Checking.class, c1.getId());
            checkCheckingData(c1,checking);
    }

    @Test
    public void should_save_a_checking_account_with_constructor() {
        u1 = new User(null,
                "Jhon",
                "Aristizabal",
                "jfaswis25@gmail.com",
                "11231184W",
                "password",
                LocalDate.of(1997, 3, 2),
                LocalDate.now(),
                false,
                Role.USER,
                checkings,
                savings);
        testEntityManager.persistAndFlush(u1);

        Checking checking = new Checking(BigDecimal.valueOf(0L),
                BigDecimal.valueOf(40L),
                BigDecimal.valueOf(1000),
                LocalDate.now(),
                Status.ACTIVE,
                false,
                BigDecimal.valueOf(12),
                u1);

        u1.getCheckings().add(checking);

        testEntityManager.persistAndFlush(checking);

        Checking checkingResponse = testEntityManager.find(Checking.class, checking.getId());
        checkCheckingData(checking, checkingResponse);
    }

    @Test
    public void should_set_id_checking() {
        Checking checking = new Checking();

        Long expectedId = 12345L;
        checking.setId(expectedId);

        Long actualId = checking.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void should_set_user_in_checking() {
        u1 = new User(null,
                "Jhon",
                "Aristizabal",
                "jfaswis25@gmail.com",
                "11231184W",
                "password",
                LocalDate.of(1997, 3, 2),
                LocalDate.now(),
                false,
                Role.USER,
                checkings,
                savings);

        testEntityManager.persistAndFlush(u1);

        c1.setUser(u1);

        testEntityManager.persistAndFlush(c1);

        Checking checking = testEntityManager.find(Checking.class, c1.getId());

        assertEquals(checking.getUser(), u1);
    }

//---------------------------------- USER ---------------------------------------
    @Test
    public void should_set_checking_and_saving_user() {
        User user = new User();

        List<Checking> checkingList = new ArrayList<>();
        user.setCheckings(checkingList);

        List<Saving> savingList = new ArrayList<>();
        user.setSavings(savingList);

        Checking checking1 = new Checking();
        user.getCheckings().add(checking1);

        Saving saving1 = new Saving();
        user.getSavings().add(saving1);

        assertEquals(1, user.getCheckings().size());
        assertEquals(1, user.getSavings().size());
        Assertions.assertNotNull(user.getCheckings());
        Assertions.assertNotNull(user.getSavings());
    }

    @Test
    public void should_set_id_user() {
        u1 = new User();

        Long expectedId = 12345L;
        u1.setId(expectedId);

        Long actualId = u1.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void should_save_a_user_with_setters() {

        u1 = new User();
        u1.setName("Jhon");
        u1.setLastName("Aristizabal");
        u1.setDni("23330241X");
        u1.setEmail("jfaswis22@gmail.com");
        u1.setPassword("password");
        u1.setDeleted(false);
        u1.setCreationDate(LocalDate.now());
        u1.setDateOfBirth(LocalDate.of(1997,3,2));
        u1.setCheckings(checkings);
        u1.setSavings(savings);
        u1.setRole(Role.USER);

        checkings.add(c1);
        savings.add(s1);

        testEntityManager.persistAndFlush(u1);
        User user = testEntityManager.find(User.class, u1.getId());
        checkUserData(u1,user);
    }

    @Test
    public void should_save_a_user_with_constructor() {


        u1 = new User(null,
                "Jhon",
                "Aristizabal",
                "jfaswis25@gmail.com",
                "11231184W",
                "password",
                LocalDate.of(1997, 3, 2),
                LocalDate.now(),
                false,
                Role.USER,
                checkings,
                savings);

        checkings.add(c1);
        savings.add(s1);

        testEntityManager.persistAndFlush(u1);
        User user = testEntityManager.find(User.class, u1.getId());

        checkUserData(u1, user);
    }

    @Test
    public void should_save_a_user_with_builder_pattern() {
        u1 = User.builder()
                .id(null)
                .name("Jhon")
                .lastName("Aristizabal")
                .email("jfaswis26@gmail.com")
                .dni("65370142W")
                .password("password")
                .dateOfBirth(LocalDate.of(1997,3,2))
                .creationDate(LocalDate.now())
                .deleted(false)
                .role(Role.USER)
                .checkings(checkings)
                .savings(savings).build();

        checkings.add(c1);
        savings.add(s1);

        testEntityManager.persistAndFlush(u1);
        User user = testEntityManager.find(User.class, u1.getId());

        checkUserData(u1,user);
    }

    public void checkUserData(User u1, User u2) {
        assertThat(u1.getId()).isEqualTo(u2.getId());
        assertThat(u1.getName()).isEqualTo(u2.getName());
        assertThat(u1.getLastName()).isEqualTo(u2.getLastName());
        assertThat(u1.getDni()).isEqualTo(u2.getDni());
        assertThat(u1.getCreationDate()).isEqualTo(u2.getCreationDate());
        assertThat(u1.getPassword()).isEqualTo(u2.getPassword());
        assertThat(u1.getEmail()).isEqualTo(u2.getEmail());
        assertThat(u1.getDateOfBirth()).isEqualTo(u2.getDateOfBirth());
        assertThat(u1.isDeleted()).isEqualTo(u2.isDeleted());
        assertThat(u1.getRole()).isEqualTo(u2.getRole());
        assertEquals(u1.getCheckings(), u2.getCheckings());
        assertEquals(u1.getSavings(), u2.getSavings());
    }

    public void checkCheckingData(Checking c1, Checking c2) {
        assertThat(c1.getId()).isEqualTo(c2.getId());
        assertThat(c1.getUser()).isEqualTo(c2.getUser());
        assertThat(c1.getMonthlyMaintenanceFee()).isEqualTo(c2.getMonthlyMaintenanceFee());
        assertThat(c1.getCreationDate()).isEqualTo(c2.getCreationDate());
        assertThat(c1.getBalance()).isEqualTo(c2.getBalance());
        assertThat(c1.getMinimumBalance()).isEqualTo(c2.getMinimumBalance());
        assertThat(c1.getStatus()).isEqualTo(c2.getStatus());
        assertThat(c1.getPenaltyFee()).isEqualTo(c2.getPenaltyFee());
        assertThat(c1.isDeleted()).isEqualTo(c2.isDeleted());
    }

    public void checkSavingData(Saving s1, Saving s2) {
        assertThat(s1.getId()).isEqualTo(s2.getId());
        assertThat(s1.getUser()).isEqualTo(s2.getUser());
        assertThat(s1.getInterestRate()).isEqualTo(s2.getInterestRate());
        assertThat(s1.getCreationDate()).isEqualTo(s2.getCreationDate());
        assertThat(s1.getBalance()).isEqualTo(s2.getBalance());
        assertThat(s1.getMinimumBalance()).isEqualTo(s2.getMinimumBalance());
        assertThat(s1.getStatus()).isEqualTo(s2.getStatus());
        assertThat(s1.getPenaltyFee()).isEqualTo(s2.getPenaltyFee());
        assertThat(s1.isDeleted()).isEqualTo(s2.isDeleted());
    }
}
