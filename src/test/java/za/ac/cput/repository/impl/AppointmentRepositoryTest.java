/* AppointmentRepositoryTest.java
Appointment Model Class
Author: Joshua Peter Bonzet (221312536)
Date: 26 March 2026
*/

package za.ac.cput.repository.impl;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.Appointment;
import za.ac.cput.factory.AppointmentFactory;
import za.ac.cput.repository.IAppointmentRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppointmentRepositoryTest {

    private static AppointmentRepository repository;
    private static Appointment appointment1;
    private static Appointment appointment2;

    @BeforeAll
    static void setUp() throws Exception {
        java.lang.reflect.Field field = AppointmentRepository.class.getDeclaredField("repository");
        field.setAccessible(true);
        field.set(null, null);
        repository = AppointmentRepository.getRepository();

        appointment1 = AppointmentFactory.createAppointment(
                1,
                LocalDate.of(2026, 4, 10),
                LocalTime.of(9, 0)
        );

        appointment2 = AppointmentFactory.createAppointment(
                2,
                LocalDate.of(2026, 4, 15),
                LocalTime.of(14, 30)
        );
    }

    @Test
    @Order(1)
    void testSingleton_SameInstance() {
        AppointmentRepository instance1 = AppointmentRepository.getRepository();
        AppointmentRepository instance2 = AppointmentRepository.getRepository();
        assertSame(instance1, instance2);
    }

    @Test
    @Order(2)
    void testImplementsInterface() {
        assertTrue(repository instanceof IAppointmentRepository);
    }

    @Test
    @Order(3)
    void testCreate_Success() {
        Appointment created = repository.create(appointment1);
        assertNotNull(created);
        assertEquals(appointment1.getAppointmentId(), created.getAppointmentId());
        assertEquals(appointment1.getAppointmentDate(), created.getAppointmentDate());
        assertEquals(appointment1.getAppointmentTime(), created.getAppointmentTime());
        System.out.println(created);
    }

    @Test
    @Order(4)
    void testCreate_Duplicate_Fails() {
        Appointment duplicate = repository.create(appointment1);
        assertNull(duplicate);
    }

    @Test
    @Order(5)
    void testRead_Success() {
        Appointment read = repository.read(1);
        assertNotNull(read);
        assertEquals(1, read.getAppointmentId());
        System.out.println(read);
    }

    @Test
    @Order(6)
    void testRead_NonExistent_ReturnsNull() {
        Appointment read = repository.read(999);
        assertNull(read);
    }

    @Test
    @Order(7)
    void testUpdate_Success() {
        Appointment updated = AppointmentFactory.createAppointment(
                1,
                LocalDate.of(2026, 6, 20),
                LocalTime.of(11, 0)
        );
        Appointment result = repository.update(updated);
        assertNotNull(result);
        assertEquals(LocalDate.of(2026, 6, 20), result.getAppointmentDate());
        assertEquals(LocalTime.of(11, 0), result.getAppointmentTime());
        System.out.println(result);
    }

    @Test
    @Order(8)
    void testUpdate_NonExistent_ReturnsNull() {
        Appointment nonExistent = AppointmentFactory.createAppointment(
                999,
                LocalDate.of(2026, 7, 1),
                LocalTime.of(8, 0)
        );
        Appointment result = repository.update(nonExistent);
        assertNull(result);
    }

    @Test
    @Order(9)
    void testGetAll_Success() {
        repository.create(appointment2);
        Set<Appointment> all = repository.getAll();
        assertFalse(all.isEmpty());
        assertTrue(all.size() >= 1);
        System.out.println(all);
    }

    @Test
    @Order(10)
    void testGetAll_IsDefensiveCopy() {
        Set<Appointment> setA = repository.getAll();
        Set<Appointment> setB = repository.getAll();
        assertNotSame(setA, setB);
    }

    @Test
    @Order(11)
    void testDelete_Success() {
        boolean deleted = repository.delete(2);
        assertTrue(deleted);
        assertNull(repository.read(2));
    }

    @Test
    @Order(12)
    void testDelete_NonExistent_ReturnsFalse() {
        boolean deleted = repository.delete(999);
        assertFalse(deleted);
    }
}