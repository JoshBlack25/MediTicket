/* PatientTicketFactoryTest.java
   Author: Joshua A (230317693)
   Date: 21 March 2026
*/
package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.*;
import za.ac.cput.domain.enums.ConfirmationStatus;
import za.ac.cput.domain.enums.StatusType;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientTicketFactoryTest {

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

        ticket = PatientTicketFactory.createTicket(
                1, "Patient requesting prescription refill",
                patient, appointment);
    }

    @Test
    @Order(1)
    void testCreateTicket_Success() {
        assertNotNull(ticket);
    }

    @Test
    @Order(2)
    void testTicketId() {
        assertEquals(1, ticket.getTicketId());
    }

    @Test
    @Order(3)
    void testTicketDescription() {
        assertEquals("Patient requesting prescription refill",
                ticket.getTicketDescription());
    }

    @Test
    @Order(4)
    void testTicketCreatedDateNotNull() {
        assertNotNull(ticket.getTicketCreatedDate());
    }

    @Test
    @Order(5)
    void testTicketPatientNotNull() {
        assertNotNull(ticket.getPatient());
    }

    @Test
    @Order(6)
    void testTicketAppointmentNotNull() {
        assertNotNull(ticket.getAppointment());
    }

    @Test
    @Order(7)
    void testCurrentStatusBeforeOpen() {
        assertNull(ticket.getCurrentStatus());
    }

    @Test
    @Order(8)
    void testAddStatusOpen() {
        ticket.addStatus(StatusType.OPEN);
        assertEquals(StatusType.OPEN, ticket.getCurrentStatus());
    }

    @Test
    @Order(9)
    void testAddStatusInProgress() {
        ticket.addStatus(StatusType.IN_PROGRESS);
        assertEquals(StatusType.IN_PROGRESS, ticket.getCurrentStatus());
    }

    @Test
    @Order(10)
    void testStatusHistoryGrows() {
        assertTrue(ticket.getStatusHistory().size() >= 1);
    }

    @Test
    @Order(11)
    void testInvalidId_Fails() {
        PatientTicket invalid = PatientTicketFactory.createTicket(
                0, "Description", patient, appointment);
        assertNull(invalid);
    }

    @Test
    @Order(12)
    void testEmptyDescription_Fails() {
        PatientTicket invalid = PatientTicketFactory.createTicket(
                2, "", patient, appointment);
        assertNull(invalid);
    }

    @Test
    @Order(13)
    void testNullPatient_Fails() {
        PatientTicket invalid = PatientTicketFactory.createTicket(
                2, "Description", null, appointment);
        assertNull(invalid);
    }

    @Test
    @Order(14)
    void testNullAppointment_Fails() {
        PatientTicket invalid = PatientTicketFactory.createTicket(
                2, "Description", patient, null);
        assertNull(invalid);
    }

    @Test
    @Order(15)
    void testUnconfirmedAppointment_Fails() {
        Appointment unconfirmed = new Appointment.Builder()
                .setAppointmentId(2)
                .setAppointmentDate(LocalDate.of(2026, 4, 10))
                .setAppointmentTime(LocalTime.of(9, 0))
                .setConfirmationStatus(ConfirmationStatus.PENDING)
                .build();
        PatientTicket invalid = PatientTicketFactory.createTicket(
                2, "Description", patient, unconfirmed);
        assertNull(invalid);
    }

    @Test
    @Order(16)
    void testCopyBuilder() {
        PatientTicket copy = new PatientTicket.Builder()
                .copy(ticket)
                .build();
        assertNotNull(copy);
        assertEquals(ticket.getTicketId(), copy.getTicketId());
        assertEquals(ticket.getTicketDescription(), copy.getTicketDescription());
    }

    @Test
    @Order(17)
    void testToString() {
        String result = ticket.toString();
        assertNotNull(result);
        assertTrue(result.contains("prescription refill"));
    }
}