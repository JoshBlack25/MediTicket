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
import za.ac.cput.domain.enums.StatusType;

import static org.junit.jupiter.api.Assertions.*;


public class TicketStatusFactoryTest {

    private TicketStatus openStatus;
    private TicketStatus closedStatus;
    private TicketStatus escalatedStatus;

    @BeforeEach
    void setUp() {
        PatientTicket ticket = PatientTicketFactory.createTicket(1, "Patient requesting prescription refill", new Patient(), new Appointment());

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
