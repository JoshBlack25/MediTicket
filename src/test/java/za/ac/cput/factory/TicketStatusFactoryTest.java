/* TicketStatusFactoryTest.java
   TDD test class for TicketStatusFactory
   Author: Joshua A (230317693)
   Date: 21 March 2026
*/

package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import za.ac.cput.domain.Appointment;
import za.ac.cput.domain.Patient;
import za.ac.cput.domain.PatientTicket;
import za.ac.cput.domain.TicketStatus;
import za.ac.cput.domain.enums.ConfirmationStatus;
import za.ac.cput.domain.enums.StatusType;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


public class TicketStatusFactoryTest {

    private TicketStatus openStatus;
    private TicketStatus closedStatus;
    private TicketStatus escalatedStatus;

    @BeforeEach
    void setUp() {
        Patient patient = new Patient.Builder()
                .setPatientId(1)
                .setPatientName("John")
                .setPatientSurname("Doe")
                .setPatientCell("1234567890")
                .setPatientEmail("john.doe@example.com")
                .setPatientDOB(LocalDate.of(1990, 1, 1))
                .build();

        Appointment appointment = new Appointment.Builder()
                .setAppointmentId(1)
                .setAppointmentDate(LocalDate.of(2026, 3, 27))
                .setAppointmentTime(LocalTime.of(9, 0))
                .setConfirmationStatus(ConfirmationStatus.PENDING) // adjust as needed
                .setDoctor(null) // or provide a valid Doctor object
                .setStaff(null) // or provide a valid ClinicStaff object
                .build();

        PatientTicket ticket = PatientTicketFactory.createTicket(1, "Patient requesting prescription refill", patient, appointment);

        openStatus      = TicketStatusFactory.createStatus(1, StatusType.OPEN, ticket);
        closedStatus    = TicketStatusFactory.createStatus(2, StatusType.CLOSED, ticket);
        escalatedStatus = TicketStatusFactory.createStatus(3, StatusType.ESCALATED, ticket);
    }

    @Test
    void testCreateStatus(){
        assertNotNull(openStatus);
    }

    @Test
    void testStatusId(){
        assertEquals(1, openStatus.getStatusId());
    }

    @Test
    void testStatusType(){
        assertEquals(StatusType.OPEN, openStatus.getStatusType());
    }

    @Test
    void testStatusDateNotNull(){
        assertNotNull(openStatus.getStatusDate());
    }

    @Test
    void testIsClosed(){
        assertTrue(closedStatus.isClosed());
        assertFalse(openStatus.isClosed());
    }

    @Test
    void testIsEscalated(){
        assertTrue(escalatedStatus.isEscalated());
        assertFalse(openStatus.isEscalated());
    }
}
