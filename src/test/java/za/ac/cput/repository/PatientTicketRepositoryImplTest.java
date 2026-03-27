package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Appointment;
import za.ac.cput.domain.Patient;
import za.ac.cput.domain.PatientTicket;
import za.ac.cput.domain.enums.ConfirmationStatus;
import za.ac.cput.factory.PatientTicketFactory;
import za.ac.cput.repository.impl.PatientTicketRepositoryImpl;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientTicketRepositoryImplTest {

    private PatientTicketRepositoryImpl repository;
    private PatientTicket ticket;

    @BeforeEach
    void setUp() {
        repository = PatientTicketRepositoryImpl.getInstance();

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
    @Order(1)
    void testCreate() {
        PatientTicket created = repository.create(ticket);
        assertNotNull(created);
        assertEquals(ticket.getTicketId(), created.getTicketId());
    }

    @Test
    @Order(2)
    void testRead() {
        repository.create(ticket);
        PatientTicket read = repository.read(1);
        assertNotNull(read);
        assertEquals(1, read.getTicketId());
    }

    @Test
    @Order(3)
    void testUpdate() {
        repository.create(ticket);

        Patient updatedPatient = new Patient.Builder()
                .setPatientId(2)
                .setPatientName("Jane")
                .setPatientSurname("Smith")
                .setPatientCell("0987654321")
                .setPatientEmail("jane.smith@example.com")
                .setPatientDOB(LocalDate.of(1985, 5, 15))
                .build();

        PatientTicket updated = new PatientTicket.Builder()
                .setTicketId(1)
                .setTicketDescription("Updated description")
                .setPatient(updatedPatient)
                .build();

        PatientTicket result = repository.update(updated);
        assertNotNull(result);
        assertEquals("Updated description", result.getTicketDescription());
    }

    @Test
    @Order(4)
    void testDelete() {
        repository.create(ticket);
        boolean deleted = repository.delete(1);
        assertTrue(deleted);
        assertNull(repository.read(1));
    }

    @Test
    @Order(5)
    void testGetAll() {
        repository.create(ticket);

        Patient patient2 = new Patient.Builder()
                .setPatientId(2)
                .setPatientName("Alice")
                .setPatientSurname("Brown")
                .setPatientCell("5551234567")
                .setPatientEmail("alice.brown@example.com")
                .setPatientDOB(LocalDate.of(1992, 7, 20))
                .build();

        Appointment appointment2 = new Appointment.Builder()
                .setAppointmentId(1)
                .setAppointmentDate(LocalDate.of(2026, 3, 27))
                .setAppointmentTime(LocalTime.of(9, 0))
                .setConfirmationStatus(ConfirmationStatus.PENDING) // adjust as needed
                .setDoctor(null) // or provide a valid Doctor object
                .setStaff(null) // or provide a valid ClinicStaff object
                .build();

        PatientTicket ticket2 = PatientTicketFactory.createTicket(2, "Follow-up appointment request", patient2, appointment2);
        repository.create(ticket2);

        assertFalse(repository.getAll().isEmpty());
        assertTrue(repository.getAll().size() >= 2);
    }
}
