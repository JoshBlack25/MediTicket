// Raul Everts 230270565
package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.*;
import za.ac.cput.domain.enums.*;
import za.ac.cput.factory.NotificationFactory;
import za.ac.cput.repository.impl.NotificationRepositoryImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NotificationRepositoryImplTest {

    private static NotificationRepositoryImpl repository;
    private static NotificationService notification1;
    private static NotificationService notification2;
    private static Patient patient;
    private static Appointment appointment;
    private static PatientTicket ticket;

    @BeforeAll
    static void setUp() throws Exception {
        java.lang.reflect.Field field =
                NotificationRepositoryImpl.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, null);

        repository = NotificationRepositoryImpl.getInstance();

        patient = new Patient.Builder()
                .setPatientId(1)
                .setPatientName("John")
                .setPatientSurname("Doe")
                .setPatientCell("0821234567")
                .setPatientEmail("john.doe@example.com")
                .setPatientDOB(LocalDate.of(1990, 1, 1))
                .build();

        appointment = new Appointment.Builder()
                .setAppointmentId(1)
                .setAppointmentDate(LocalDate.of(2026, 4, 10))
                .setAppointmentTime(LocalTime.of(9, 0))
                .setConfirmationStatus(ConfirmationStatus.CONFIRMED)
                .build();

        ticket = new PatientTicket.Builder()
                .setTicketId(1)
                .setTicketDescription("General checkup")
                .setPatient(patient)
                .setAppointment(appointment)
                .build();

        notification1 = NotificationFactory.createSMS(
                1, "Your appointment is confirmed",
                patient, ticket, appointment);

        notification2 = NotificationFactory.createEMAIL(
                2, "Your payment was received",
                patient, ticket, appointment);
    }

    @Test
    @Order(1)
    void testSingleton_SameInstance() {
        NotificationRepositoryImpl instance1 = NotificationRepositoryImpl.getInstance();
        NotificationRepositoryImpl instance2 = NotificationRepositoryImpl.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @Order(2)
    void testCreate_Success() {
        NotificationService created = repository.create(notification1);
        assertNotNull(created);
        assertEquals(1, created.getNotificationId());
        assertEquals(NotificationType.SMS, created.getNotificationType());
        System.out.println(created);
    }

    @Test
    @Order(3)
    void testCreate_Null_Fails() {
        NotificationService created = repository.create(null);
        assertNull(created);
    }

    @Test
    @Order(4)
    void testCreate_Duplicate_Fails() {
        NotificationService duplicate = repository.create(notification1);
        assertNull(duplicate);
    }

    @Test
    @Order(5)
    void testRead_Success() {
        NotificationService found = repository.read(1);
        assertNotNull(found);
        assertEquals(1, found.getNotificationId());
        System.out.println(found);
    }

    @Test
    @Order(6)
    void testRead_NonExistent_Fails() {
        NotificationService found = repository.read(999);
        assertNull(found);
    }

    @Test
    @Order(7)
    void testRead_InvalidId_Fails() {
        NotificationService found = repository.read(0);
        assertNull(found);
    }

    @Test
    @Order(8)
    void testUpdate_Success() {
        NotificationService updated = NotificationFactory.createEMAIL(
                1, "Updated message",
                patient, ticket, appointment);
        NotificationService result = repository.update(updated);
        assertNotNull(result);
        assertEquals(NotificationType.EMAIL, result.getNotificationType());
        assertEquals("Updated message", result.getNotificationMessage());
        System.out.println(result);
    }

    @Test
    @Order(9)
    void testUpdate_NonExistent_Fails() {
        NotificationService nonExistent = NotificationFactory.createSMS(
                999, "Ghost message",
                patient, ticket, appointment);
        NotificationService result = repository.update(nonExistent);
        assertNull(result);
    }

    @Test
    @Order(10)
    void testUpdate_Null_Fails() {
        NotificationService result = repository.update(null);
        assertNull(result);
    }

    @Test
    @Order(11)
    void testGetAll_NotEmpty() {
        repository.create(notification2);
        List<NotificationService> all = repository.getAll();
        assertNotNull(all);
        assertFalse(all.isEmpty());
        System.out.println(all);
    }

    @Test
    @Order(12)
    void testGetAll_IsDefensiveCopy() {
        List<NotificationService> listA = repository.getAll();
        List<NotificationService> listB = repository.getAll();
        assertNotSame(listA, listB);
    }

    @Test
    @Order(13)
    void testDelete_Success() {
        boolean deleted = repository.delete(2);
        assertTrue(deleted);
        assertNull(repository.read(2));
    }

    @Test
    @Order(14)
    void testDelete_NonExistent_Fails() {
        boolean deleted = repository.delete(999);
        assertFalse(deleted);
    }

    @Test
    @Order(15)
    void testDelete_InvalidId_Fails() {
        boolean deleted = repository.delete(0);
        assertFalse(deleted);
    }
}