/* PaymentRepositoryImplTest.java
   Author: Abdullahi (230971091)
   Date: 25 March 2026
*/
package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.*;
import za.ac.cput.domain.enums.*;
import za.ac.cput.factory.PaymentFactory;
import za.ac.cput.repository.impl.PaymentRepositoryImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentRepositoryImplTest {

    private static PaymentRepositoryImpl repository;
    private static Payment payment1;
    private static Payment payment2;
    private static PatientTicket ticket;

    @BeforeAll
    static void setUp() throws Exception {
        java.lang.reflect.Field field =
                PaymentRepositoryImpl.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, null);

        repository = PaymentRepositoryImpl.getInstance();

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

        ticket = new PatientTicket.Builder()
                .setTicketId(201)
                .setTicketDescription("Follow-up visit")
                .setPatient(patient)
                .setAppointment(appointment)
                .build();

        payment1 = PaymentFactory.createPayment(
                1, 750.00,
                LocalDateTime.of(2026, 3, 25, 9, 30),
                PaymentMethod.EFT,
                PaymentStatus.PAID,
                ticket
        );

        payment2 = PaymentFactory.createPayment(
                2, 500.00,
                LocalDateTime.of(2026, 3, 26, 10, 0),
                PaymentMethod.CARD,
                PaymentStatus.PENDING,
                ticket
        );
    }

    @Test
    @Order(1)
    void testSingleton_SameInstance() {
        PaymentRepositoryImpl instance1 = PaymentRepositoryImpl.getInstance();
        PaymentRepositoryImpl instance2 = PaymentRepositoryImpl.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @Order(2)
    void testCreate_Success() {
        Payment created = repository.create(payment1);
        assertNotNull(created);
        assertEquals(1, created.getPaymentId());
        assertEquals(750.00, created.getPaymentAmount());
        assertEquals(PaymentMethod.EFT, created.getPaymentMethod());
        System.out.println(created);
    }

    @Test
    @Order(3)
    void testCreate_Null_Fails() {
        Payment created = repository.create(null);
        assertNull(created);
    }

    @Test
    @Order(4)
    void testCreate_Duplicate_Fails() {
        Payment duplicate = repository.create(payment1);
        assertNull(duplicate);
    }

    @Test
    @Order(5)
    void testRead_Success() {
        Payment read = repository.read(1);
        assertNotNull(read);
        assertEquals(1, read.getPaymentId());
        System.out.println(read);
    }

    @Test
    @Order(6)
    void testRead_NonExistent_Fails() {
        Payment read = repository.read(999);
        assertNull(read);
    }

    @Test
    @Order(7)
    void testRead_InvalidId_Fails() {
        Payment read = repository.read(0);
        assertNull(read);
    }

    @Test
    @Order(8)
    void testUpdate_Success() {
        Payment updated = PaymentFactory.createPayment(
                1, 900.00,
                LocalDateTime.now(),
                PaymentMethod.MEDICAL_AID,
                PaymentStatus.PENDING,
                ticket
        );
        Payment result = repository.update(updated);
        assertNotNull(result);
        assertEquals(900.00, result.getPaymentAmount());
        assertEquals(PaymentMethod.MEDICAL_AID, result.getPaymentMethod());
        System.out.println(result);
    }

    @Test
    @Order(9)
    void testUpdate_NonExistent_Fails() {
        Payment nonExistent = PaymentFactory.createPayment(
                999, 100.00,
                LocalDateTime.now(),
                PaymentMethod.CASH,
                PaymentStatus.PENDING,
                ticket
        );
        Payment result = repository.update(nonExistent);
        assertNull(result);
    }

    @Test
    @Order(10)
    void testUpdate_Null_Fails() {
        Payment result = repository.update(null);
        assertNull(result);
    }

    @Test
    @Order(11)
    void testGetAll_NotEmpty() {
        repository.create(payment2);
        List<Payment> all = repository.getAll();
        assertNotNull(all);
        assertFalse(all.isEmpty());
        System.out.println(all);
    }

    @Test
    @Order(12)
    void testGetAll_IsDefensiveCopy() {
        List<Payment> listA = repository.getAll();
        List<Payment> listB = repository.getAll();
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