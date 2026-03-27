// Raul Everts 230270565
package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.*;
import za.ac.cput.domain.enums.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NotificationFactoryTest {

    private static Patient patient;
    private static Appointment appointment;
    private static PatientTicket ticket;

    @BeforeAll
    static void setUp() {
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
    }

    // SMS tests
    @Test
    @Order(1)
    void testCreateSMS_Success() {
        NotificationService notification = NotificationFactory.createSMS(
                1, "Your appointment is confirmed",
                patient, ticket, appointment);
        assertNotNull(notification);
        assertEquals(NotificationType.SMS, notification.getNotificationType());
        assertEquals(NotificationStatus.PENDING, notification.getNotificationStatus());
        assertEquals("Your appointment is confirmed", notification.getNotificationMessage());
        assertNotNull(notification.getNotificationDate());
        assertNotNull(notification.getPatient());
        assertNotNull(notification.getTicket());
        assertNotNull(notification.getAppointment());
    }

    @Test
    @Order(2)
    void testCreateSMS_InvalidId_Fails() {
        NotificationService notification = NotificationFactory.createSMS(
                0, "Message", patient, ticket, appointment);
        assertNull(notification);
    }

    @Test
    @Order(3)
    void testCreateSMS_EmptyMessage_Fails() {
        NotificationService notification = NotificationFactory.createSMS(
                1, "", patient, ticket, appointment);
        assertNull(notification);
    }

    @Test
    @Order(4)
    void testCreateSMS_NullPatient_Fails() {
        NotificationService notification = NotificationFactory.createSMS(
                1, "Message", null, ticket, appointment);
        assertNull(notification);
    }

    @Test
    @Order(5)
    void testCreateSMS_NullTicket_Fails() {
        NotificationService notification = NotificationFactory.createSMS(
                1, "Message", patient, null, appointment);
        assertNull(notification);
    }

    @Test
    @Order(6)
    void testCreateSMS_NullAppointment_Fails() {
        NotificationService notification = NotificationFactory.createSMS(
                1, "Message", patient, ticket, null);
        assertNull(notification);
    }

    // EMAIL tests
    @Test
    @Order(7)
    void testCreateEMAIL_Success() {
        NotificationService notification = NotificationFactory.createEMAIL(
                2, "Your appointment is confirmed",
                patient, ticket, appointment);
        assertNotNull(notification);
        assertEquals(NotificationType.EMAIL, notification.getNotificationType());
        assertEquals(NotificationStatus.PENDING, notification.getNotificationStatus());
        assertEquals("Your appointment is confirmed", notification.getNotificationMessage());
        assertNotNull(notification.getNotificationDate());
    }

    @Test
    @Order(8)
    void testCreateEMAIL_InvalidId_Fails() {
        NotificationService notification = NotificationFactory.createEMAIL(
                0, "Message", patient, ticket, appointment);
        assertNull(notification);
    }

    @Test
    @Order(9)
    void testCreateEMAIL_EmptyMessage_Fails() {
        NotificationService notification = NotificationFactory.createEMAIL(
                2, "", patient, ticket, appointment);
        assertNull(notification);
    }

    @Test
    @Order(10)
    void testCreateEMAIL_NullPatient_Fails() {
        NotificationService notification = NotificationFactory.createEMAIL(
                2, "Message", null, ticket, appointment);
        assertNull(notification);
    }

    @Test
    @Order(11)
    void testCreateEMAIL_NullTicket_Fails() {
        NotificationService notification = NotificationFactory.createEMAIL(
                2, "Message", patient, null, appointment);
        assertNull(notification);
    }

    @Test
    @Order(12)
    void testCreateEMAIL_NullAppointment_Fails() {
        NotificationService notification = NotificationFactory.createEMAIL(
                2, "Message", patient, ticket, null);
        assertNull(notification);
    }

    @Test
    @Order(13)
    void testToString() {
        NotificationService notification = NotificationFactory.createSMS(
                3, "Test message", patient, ticket, appointment);
        assertNotNull(notification);
        String result = notification.toString();
        assertNotNull(result);
        assertTrue(result.contains("SMS"));
        assertTrue(result.contains("Test message"));
    }
}