package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Appointment;
import za.ac.cput.domain.Patient;
import za.ac.cput.domain.PatientTicket;
import za.ac.cput.domain.TicketStatus;
import za.ac.cput.domain.enums.StatusType;
import za.ac.cput.factory.PatientTicketFactory;
import za.ac.cput.factory.TicketStatusFactory;
import za.ac.cput.repository.impl.TicketStatusRepositoryImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketStatusRepositoryImplTest {

    private TicketStatusRepositoryImpl repository;
    private TicketStatus status;
    private PatientTicket ticket;

    @BeforeEach
    void setUp() {
        repository = TicketStatusRepositoryImpl.getInstance();

        Patient patient = new Patient.Builder()
                .setPatientId(1)
                .setPatientName("John")
                .setPatientSurname("Doe")
                .setPatientCell("1234567890")
                .setPatientEmail("john.doe@example.com")
                .setPatientDOB(LocalDate.of(1990, 1, 1))
                .build();

        Appointment appointment = new Appointment();

        ticket = PatientTicketFactory.createTicket(1, "Patient requesting prescription refill", patient, appointment);

        status = TicketStatusFactory.createStatus(1, StatusType.OPEN, ticket);
    }

    @Test
    @Order(1)
    void testCreate() {
        TicketStatus created = repository.create(status);
        assertNotNull(created);
        assertEquals(status.getStatusId(), created.getStatusId());
    }

    @Test
    @Order(2)
    void testRead() {
        repository.create(status);
        TicketStatus read = repository.read(1);
        assertNotNull(read);
        assertEquals(1, read.getStatusId());
    }

    @Test
    @Order(3)
    void testUpdate() {
        repository.create(status);
        TicketStatus updated = TicketStatusFactory.createStatus(1, StatusType.IN_PROGRESS, ticket);
        TicketStatus result = repository.update(updated);
        assertNotNull(result);
        assertEquals(StatusType.IN_PROGRESS, result.getStatusType());
    }

    @Test
    @Order(4)
    void testDelete() {
        repository.create(status);
        boolean deleted = repository.delete(1);
        assertTrue(deleted);
        assertNull(repository.read(1));
    }

    @Test
    @Order(5)
    void testGetAll() {
        repository.create(status);
        TicketStatus status2 = TicketStatusFactory.createStatus(2, StatusType.ESCALATED, ticket);
        repository.create(status2);
        assertFalse(repository.getAll().isEmpty());
        assertTrue(repository.getAll().size() >= 2);
    }
}
