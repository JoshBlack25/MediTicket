/* TicketStatusFactoryTest.java
   Author: Joshua A (230317693)
   Date: 21 March 2026
*/
package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.*;
import za.ac.cput.domain.enums.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketStatusFactoryTest {

    private static PatientTicket ticket;
    private static TicketStatus openStatus;
    private static TicketStatus closedStatus;
    private static TicketStatus escalatedStatus;

    @BeforeAll
    static void setUp() {
        Patient patient = new Patient.Builder()
                .setPatientId(1)
                .setPatientName("John")
                .setPatientSurname("Doe")
                .setPatientCell("0821234567")
                .setPatientEmail("john.doe@example.com")
                .setPatientDOB(LocalDate.of(1990, 1, 1))
                .build();

        Appointment appointment = new Appointment.Builder()
                .setAppointmentId(1)
                .setAppointmentDate(LocalDate.of(2026, 4, 10))
                .setAppointmentTime(LocalTime.of(9, 0))
                .setConfirmationStatus(ConfirmationStatus.CONFIRMED)
                .build();

        ticket = PatientTicketFactory.createTicket(
                1, "Patient requesting prescription refill",
                patient, appointment);

        openStatus      = TicketStatusFactory.createStatus(1, StatusType.OPEN, ticket);
        closedStatus    = TicketStatusFactory.createStatus(2, StatusType.CLOSED, ticket);
        escalatedStatus = TicketStatusFactory.createStatus(3, StatusType.ESCALATED, ticket);
    }

    @Test
    @Order(1)
    void testCreateStatus_Success() {
        assertNotNull(openStatus);
    }

    @Test
    @Order(2)
    void testStatusId() {
        assertEquals(1, openStatus.getStatusId());
    }

    @Test
    @Order(3)
    void testStatusType() {
        assertEquals(StatusType.OPEN, openStatus.getStatusType());
    }

    @Test
    @Order(4)
    void testStatusDateNotNull() {
        assertNotNull(openStatus.getStatusDate());
    }

    @Test
    @Order(5)
    void testTicketNotNull() {
        assertNotNull(openStatus.getTicket());
    }

    @Test
    @Order(6)
    void testIsClosed_True() {
        assertTrue(closedStatus.isClosed());
    }

    @Test
    @Order(7)
    void testIsClosed_False() {
        assertFalse(openStatus.isClosed());
    }

    @Test
    @Order(8)
    void testIsEscalated_True() {
        assertTrue(escalatedStatus.isEscalated());
    }

    @Test
    @Order(9)
    void testIsEscalated_False() {
        assertFalse(openStatus.isEscalated());
    }

    @Test
    @Order(10)
    void testInvalidId_Fails() {
        TicketStatus invalid = TicketStatusFactory.createStatus(
                0, StatusType.OPEN, ticket);
        assertNull(invalid);
    }

    @Test
    @Order(11)
    void testNullStatusType_Fails() {
        TicketStatus invalid = TicketStatusFactory.createStatus(
                4, null, ticket);
        assertNull(invalid);
    }

    @Test
    @Order(12)
    void testNullTicket_Fails() {
        TicketStatus invalid = TicketStatusFactory.createStatus(
                4, StatusType.OPEN, null);
        assertNull(invalid);
    }

    @Test
    @Order(13)
    void testCopyBuilder() {
        TicketStatus copy = new TicketStatus.Builder()
                .copy(openStatus)
                .build();
        assertNotNull(copy);
        assertEquals(openStatus.getStatusId(), copy.getStatusId());
        assertEquals(openStatus.getStatusType(), copy.getStatusType());
    }

    @Test
    @Order(14)
    void testToString() {
        String result = openStatus.toString();
        assertNotNull(result);
        assertTrue(result.contains("OPEN"));
    }
}