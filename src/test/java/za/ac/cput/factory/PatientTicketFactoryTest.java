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


import static org.junit.jupiter.api.Assertions.*;

public class PatientTicketFactoryTest {

    private PatientTicket ticket;

    @BeforeEach
    void setUp(){
        Patient patient = new Patient();
        Appointment appointment = new Appointment(); // Add this line
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
