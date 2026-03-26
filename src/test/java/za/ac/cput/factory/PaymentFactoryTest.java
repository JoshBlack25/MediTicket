/* PaymentFactoryTest.java
   TDD test class for PaymentFactory
   Author: Abdullahi (230971091)
   Date: 25 March 2026
*/
package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.enums.PaymentMethod;
import za.ac.cput.domain.enums.PaymentStatus;
import za.ac.cput.domain.PatientTicket;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentFactoryTest {

    private PatientTicket buildTicket() {
        return new PatientTicket.Builder()
                .setTicketId(101)
                .setTicketDescription("General checkup")
                .setTicketCreatedDate(LocalDateTime.of(2026, 3, 25, 9, 0))
                .build();
    }

    @Test
    void testCreatePayment_Success() {
        Payment payment = PaymentFactory.createPayment(
                1, 500.00,
                LocalDateTime.of(2026, 3, 25, 10, 0),
                PaymentMethod.CARD,
                PaymentStatus.PAID,
                buildTicket()
        );
        assertNotNull(payment);
        assertEquals(1, payment.getPaymentId());
        assertEquals(500.00, payment.getPaymentAmount());
        assertEquals(PaymentMethod.CARD, payment.getPaymentMethod());
        assertEquals(PaymentStatus.PAID, payment.getPaymentStatus());
        assertNotNull(payment.getTicket());
        System.out.println("Test passed: " + payment);
    }

    @Test
    void testCreatePayment_WithNullDate_UsesNow() {
        Payment payment = PaymentFactory.createPayment(
                2, 300.00, null,
                PaymentMethod.EFT,
                PaymentStatus.PENDING,
                buildTicket()
        );
        assertNotNull(payment);
        assertNotNull(payment.getPaymentDate());
        System.out.println("Test passed: " + payment);
    }

    @Test
    void testCreatePayment_InvalidAmount_ReturnsNull() {
        Payment payment = PaymentFactory.createPayment(
                3, -100.00,
                LocalDateTime.now(),
                PaymentMethod.CASH,
                PaymentStatus.PENDING,
                buildTicket()
        );
        assertNull(payment);
        System.out.println("Test passed: invalid amount correctly returned null");
    }

    @Test
    void testCreatePayment_NullPaymentMethod_ReturnsNull() {
        Payment payment = PaymentFactory.createPayment(
                4, 200.00,
                LocalDateTime.now(),
                null,
                PaymentStatus.PENDING,
                buildTicket()
        );
        assertNull(payment);
        System.out.println("Test passed: null method correctly returned null");
    }

    @Test
    void testCreatePayment_NullPaymentStatus_ReturnsNull() {
        Payment payment = PaymentFactory.createPayment(
                5, 200.00,
                LocalDateTime.now(),
                PaymentMethod.MEDICAL_AID,
                null,
                buildTicket()
        );
        assertNull(payment);
        System.out.println("Test passed: null status correctly returned null");
    }
}