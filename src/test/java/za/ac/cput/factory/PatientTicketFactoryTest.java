package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import za.ac.cput.domain.Patient;
import za.ac.cput.domain.PatientTicket;
import za.ac.cput.domain.enums.StatusType;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTicketFactoryTest {

    private PatientTicket ticket;

    @BeforeEach
    void setUp(){
        Patient patient = new Patient();
        ticket = PatientTicketFactory.createTicket(1, "Patient requesting prescription refill", patient);
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

    @Test
    void TestOpenTicket(){
        ticket.openTicket();
        assertEquals(StatusType.OPEN, ticket.getCurrentStatus());
        assertNotNull(ticket.getTicketCreatedDate());
    }

    @Test
    void testCloseTicket(){
        ticket.openTicket();
        ticket.closeTicket();
        assertEquals(StatusType.CLOSED, ticket.getCurrentStatus());
    }
}
