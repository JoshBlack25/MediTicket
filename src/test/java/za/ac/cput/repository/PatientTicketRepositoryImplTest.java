/* PatientTicketRepositoryImplTest.java
   Author: Joshua A (230317693)
   Date: 22 March 2026
*/
package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.*;
import za.ac.cput.domain.enums.ConfirmationStatus;
import za.ac.cput.factory.PatientTicketFactory;
import za.ac.cput.repository.impl.PatientTicketRepositoryImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientTicketRepositoryImplTest {

    private static PatientTicketRepositoryImpl repository;
    private static PatientTicket ticket1;
    private static PatientTicket ticket2;
    private static Patient patient;
    private static Appointment appointment;

    @BeforeAll
    static void setUp() throws Exception {
        java.lang.reflect.Field field =
                PatientTicketRepositoryImpl.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, null);

        repository = PatientTicketRepositoryImpl.getInstance();

        patient = new Patient.Builder()
                .setPatientId(1)
                .setPatientName("John")
                .setPatientSurname("Doe")
                .setPatientCell("0821234567")
                .setPatientEmail("john.doe@example.com")
                .setPatientDOB(LocalDate.of(1990, 1, 1))
                .build();

        appointment = new Appointment.Builder()
                .setAppointmentId(1)
                .setAppointmentDate(LocalDate.of(2026, 4, 10))
                .setAppointmentTime(LocalTime.of(9, 0))
                .setConfirmationStatus(ConfirmationStatus.CONFIRMED)
                .build();

        ticket1 = PatientTicketFactory.createTicket(
                1, "Patient requesting prescription refill",
                patient, appointment);

        ticket2 = PatientTicketFactory.createTicket(
                2, "Follow-up appointment request",
                patient, appointment);
    }

    @Test
    @Order(1)
    void testSingleton_SameInstance() {
        PatientTicketRepositoryImpl instance1 = PatientTicketRepositoryImpl.getInstance();
        PatientTicketRepositoryImpl instance2 = PatientTicketRepositoryImpl.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @Order(2)
    void testCreate_Success() {
        PatientTicket created = repository.create(ticket1);
        assertNotNull(created);
        assertEquals(1, created.getTicketId());
        assertEquals("Patient requesting prescription refill",
                created.getTicketDescription());
        System.out.println(created);
    }

    @Test
    @Order(3)
    void testCreate_Null_Fails() {
        PatientTicket created = repository.create(null);
        assertNull(created);
    }

    @Test
    @Order(4)
    void testCreate_Duplicate_Fails() {
        PatientTicket duplicate = repository.create(ticket1);
        assertNull(duplicate);
    }

    @Test
    @Order(5)
    void testRead_Success() {
        PatientTicket read = repository.read(1);
        assertNotNull(read);
        assertEquals(1, read.getTicketId());
        System.out.println(read);
    }

    @Test
    @Order(6)
    void testRead_NonExistent_Fails() {
        PatientTicket read = repository.read(999);
        assertNull(read);
    }

    @Test
    @Order(7)
    void testRead_InvalidId_Fails() {
        PatientTicket read = repository.read(0);
        assertNull(read);
    }

    @Test
    @Order(8)
    void testUpdate_Success() {
        PatientTicket updated = new PatientTicket.Builder()
                .copy(ticket1)
                .setTicketDescription("Updated description")
                .build();
        PatientTicket result = repository.update(updated);
        assertNotNull(result);
        assertEquals("Updated description", result.getTicketDescription());
        System.out.println(result);
    }

    @Test
    @Order(9)
    void testUpdate_NonExistent_Fails() {
        PatientTicket nonExistent = PatientTicketFactory.createTicket(
                999, "Ghost ticket", patient, appointment);
        PatientTicket result = repository.update(nonExistent);
        assertNull(result);
    }

    @Test
    @Order(10)
    void testUpdate_Null_Fails() {
        PatientTicket result = repository.update(null);
        assertNull(result);
    }

    @Test
    @Order(11)
    void testGetAll_NotEmpty() {
        repository.create(ticket2);
        List<PatientTicket> all = repository.getAll();
        assertNotNull(all);
        assertFalse(all.isEmpty());
        System.out.println(all);
    }

    @Test
    @Order(12)
    void testGetAll_IsDefensiveCopy() {
        List<PatientTicket> listA = repository.getAll();
        List<PatientTicket> listB = repository.getAll();
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