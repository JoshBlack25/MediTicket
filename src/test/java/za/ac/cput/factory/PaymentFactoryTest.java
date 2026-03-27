/* PaymentFactoryTest.java
   Author: Abdullahi (230971091)
   Date: 25 March 2026
*/
package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.*;
import za.ac.cput.domain.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentFactoryTest {

    private static PatientTicket ticket;

    @BeforeAll
    static void setUp() {
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
                .setTicketId(101)
                .setTicketDescription("General checkup")
                .setPatient(patient)
                .setAppointment(appointment)
                .build();
    }

    @Test
    @Order(1)
    void testCreatePayment_Success() {
        Payment payment = PaymentFactory.createPayment(
                1, 500.00,
                LocalDateTime.of(2026, 3, 25, 10, 0),
                PaymentMethod.CARD,
                PaymentStatus.PAID,
                ticket
        );
        assertNotNull(payment);
        assertEquals(1, payment.getPaymentId());
        assertEquals(500.00, payment.getPaymentAmount());
        assertEquals(PaymentMethod.CARD, payment.getPaymentMethod());
        assertEquals(PaymentStatus.PAID, payment.getPaymentStatus());
        assertNotNull(payment.getTicket());
    }

    @Test
    @Order(2)
    void testCreatePayment_NullDate_UsesNow() {
        Payment payment = PaymentFactory.createPayment(
                2, 300.00, null,
                PaymentMethod.EFT,
                PaymentStatus.PENDING,
                ticket
        );
        assertNotNull(payment);
        assertNotNull(payment.getPaymentDate());
    }

    @Test
    @Order(3)
    void testCreatePayment_InvalidId_Fails() {
        Payment payment = PaymentFactory.createPayment(
                0, 500.00,
                LocalDateTime.now(),
                PaymentMethod.CARD,
                PaymentStatus.PAID,
                ticket
        );
        assertNull(payment);
    }

    @Test
    @Order(4)
    void testCreatePayment_InvalidAmount_Fails() {
        Payment payment = PaymentFactory.createPayment(
                3, -100.00,
                LocalDateTime.now(),
                PaymentMethod.CASH,
                PaymentStatus.PENDING,
                ticket
        );
        assertNull(payment);
    }

    @Test
    @Order(5)
    void testCreatePayment_ZeroAmount_Fails() {
        Payment payment = PaymentFactory.createPayment(
                4, 0,
                LocalDateTime.now(),
                PaymentMethod.CASH,
                PaymentStatus.PENDING,
                ticket
        );
        assertNull(payment);
    }

    @Test
    @Order(6)
    void testCreatePayment_NullMethod_Fails() {
        Payment payment = PaymentFactory.createPayment(
                5, 200.00,
                LocalDateTime.now(),
                null,
                PaymentStatus.PENDING,
                ticket
        );
        assertNull(payment);
    }

    @Test
    @Order(7)
    void testCreatePayment_NullStatus_Fails() {
        Payment payment = PaymentFactory.createPayment(
                6, 200.00,
                LocalDateTime.now(),
                PaymentMethod.MEDICAL_AID,
                null,
                ticket
        );
        assertNull(payment);
    }

    @Test
    @Order(8)
    void testCreatePayment_NullTicket_Fails() {
        Payment payment = PaymentFactory.createPayment(
                7, 200.00,
                LocalDateTime.now(),
                PaymentMethod.CARD,
                PaymentStatus.PAID,
                null
        );
        assertNull(payment);
    }

    @Test
    @Order(9)
    void testCopyBuilder() {
        Payment original = PaymentFactory.createPayment(
                8, 750.00,
                LocalDateTime.now(),
                PaymentMethod.CARD,
                PaymentStatus.PAID,
                ticket
        );
        assertNotNull(original);
        Payment copy = new Payment.Builder()
                .copy(original)
                .build();
        assertNotNull(copy);
        assertEquals(original.getPaymentId(), copy.getPaymentId());
        assertEquals(original.getPaymentAmount(), copy.getPaymentAmount());
        assertEquals(original.getPaymentMethod(), copy.getPaymentMethod());
        assertEquals(original.getPaymentStatus(), copy.getPaymentStatus());
    }

    @Test
    @Order(10)
    void testToString() {
        Payment payment = PaymentFactory.createPayment(
                9, 500.00,
                LocalDateTime.now(),
                PaymentMethod.CASH,
                PaymentStatus.PENDING,
                ticket
        );
        assertNotNull(payment);
        String result = payment.toString();
        assertNotNull(result);
        assertTrue(result.contains("500.0"));
        assertTrue(result.contains("CASH"));
    }
}