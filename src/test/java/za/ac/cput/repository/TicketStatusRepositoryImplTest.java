/* TicketStatusRepositoryImplTest.java
   Author: Joshua A (230317693)
   Date: 22 March 2026
*/
package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.*;
import za.ac.cput.domain.enums.*;
import za.ac.cput.factory.PatientTicketFactory;
import za.ac.cput.factory.TicketStatusFactory;
import za.ac.cput.repository.impl.TicketStatusRepositoryImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketStatusRepositoryImplTest {

    private static TicketStatusRepositoryImpl repository;
    private static TicketStatus status1;
    private static TicketStatus status2;
    private static PatientTicket ticket;

    @BeforeAll
    static void setUp() throws Exception {
        java.lang.reflect.Field field =
                TicketStatusRepositoryImpl.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, null);

        repository = TicketStatusRepositoryImpl.getInstance();

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

        status1 = TicketStatusFactory.createStatus(1, StatusType.OPEN, ticket);
        status2 = TicketStatusFactory.createStatus(2, StatusType.ESCALATED, ticket);
    }

    @Test
    @Order(1)
    void testSingleton_SameInstance() {
        TicketStatusRepositoryImpl instance1 = TicketStatusRepositoryImpl.getInstance();
        TicketStatusRepositoryImpl instance2 = TicketStatusRepositoryImpl.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @Order(2)
    void testCreate_Success() {
        TicketStatus created = repository.create(status1);
        assertNotNull(created);
        assertEquals(1, created.getStatusId());
        assertEquals(StatusType.OPEN, created.getStatusType());
        System.out.println(created);
    }

    @Test
    @Order(3)
    void testCreate_Null_Fails() {
        TicketStatus created = repository.create(null);
        assertNull(created);
    }

    @Test
    @Order(4)
    void testCreate_Duplicate_Fails() {
        TicketStatus duplicate = repository.create(status1);
        assertNull(duplicate);
    }

    @Test
    @Order(5)
    void testRead_Success() {
        TicketStatus read = repository.read(1);
        assertNotNull(read);
        assertEquals(1, read.getStatusId());
        System.out.println(read);
    }

    @Test
    @Order(6)
    void testRead_NonExistent_Fails() {
        TicketStatus read = repository.read(999);
        assertNull(read);
    }

    @Test
    @Order(7)
    void testRead_InvalidId_Fails() {
        TicketStatus read = repository.read(0);
        assertNull(read);
    }

    @Test
    @Order(8)
    void testUpdate_Success() {
        TicketStatus updated = TicketStatusFactory.createStatus(
                1, StatusType.IN_PROGRESS, ticket);
        TicketStatus result = repository.update(updated);
        assertNotNull(result);
        assertEquals(StatusType.IN_PROGRESS, result.getStatusType());
        System.out.println(result);
    }

    @Test
    @Order(9)
    void testUpdate_NonExistent_Fails() {
        TicketStatus nonExistent = TicketStatusFactory.createStatus(
                999, StatusType.CLOSED, ticket);
        TicketStatus result = repository.update(nonExistent);
        assertNull(result);
    }

    @Test
    @Order(10)
    void testUpdate_Null_Fails() {
        TicketStatus result = repository.update(null);
        assertNull(result);
    }

    @Test
    @Order(11)
    void testGetAll_NotEmpty() {
        repository.create(status2);
        List<TicketStatus> all = repository.getAll();
        assertNotNull(all);
        assertFalse(all.isEmpty());
        System.out.println(all);
    }

    @Test
    @Order(12)
    void testGetAll_IsDefensiveCopy() {
        List<TicketStatus> listA = repository.getAll();
        List<TicketStatus> listB = repository.getAll();
        assertNotSame(listA, listB);
    }

    @Test
    @Order(13)
    void testDelete_Success() {
        boolean deleted = repository.delete(2);
        assertTrue(deleted);
        assertNull(repository.read(2));
    }

    @Test
    @Order(14)
    void testDelete_NonExistent_Fails() {
        boolean deleted = repository.delete(999);
        assertFalse(deleted);
    }

    @Test
    @Order(15)
    void testDelete_InvalidId_Fails() {
        boolean deleted = repository.delete(0);
        assertFalse(deleted);
    }
}