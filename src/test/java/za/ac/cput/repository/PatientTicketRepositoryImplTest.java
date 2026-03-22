/* PatientTicketRepositoryImplTest.java
   TDD test class for PatientTicketRepositoryImpl
   Author: Joshua A (230317693)
   Date: 22 March 2026
*/

package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Patient;
import za.ac.cput.domain.PatientTicket;
import za.ac.cput.factory.PatientTicketFactory;
import za.ac.cput.repository.impl.PatientTicketRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class PatientTicketRepositoryImplTest {

    private PatientTicketRepositoryImpl repository;
    private PatientTicket ticket;

    @BeforeEach
    void setUp() {
        repository = PatientTicketRepositoryImpl.getInstance();
        ticket = PatientTicketFactory.createTicket(1, "Patient requesting prescription refill", new Patient());
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
        PatientTicket updated = new PatientTicket.Builder()
                .setTicketId(1)
                .setTicketDescription("Updated description")
                .setPatient(new Patient())
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
        PatientTicket ticket2 = PatientTicketFactory.createTicket(2, "Follow-up appointment request", new Patient());
        repository.create(ticket2);
        assertFalse(repository.getAll().isEmpty());
        assertTrue(repository.getAll().size() >= 2);
    }

}
