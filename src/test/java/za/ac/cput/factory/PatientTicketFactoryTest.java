/* PatientTicketFactoryTest.java
   TDD test class for PatientTicketFactory
   Author: Joshua A (230317693)
   Date: 21 March 2026
*/

package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import za.ac.cput.domain.Patient;
import za.ac.cput.domain.PatientTicket;
import za.ac.cput.domain.Appointment;
import za.ac.cput.domain.enums.ConfirmationStatus;


import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTicketFactoryTest {

    private PatientTicket ticket;

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

        ticket = PatientTicketFactory.createTicket(1, "Patient requesting prescription refill", patient, appointment);
    }

    @Test
    void testCreateTicket(){
        assertNotNull(ticket);
    }

    @Test
    void testTicketId(){
        assertEquals(1., ticket.getTicketId());
    }

    @Test
    void testTicketDescription(){
        assertEquals("Patient requesting prescription refill", ticket.getTicketDescription());
    }

    @Test
    void testCurrentStatusBeforeOpen(){
        assertNull(ticket.getCurrentStatus());
    }

}
