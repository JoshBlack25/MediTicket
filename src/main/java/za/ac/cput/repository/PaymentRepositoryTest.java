/* PaymentRepositoryTest.java
   TDD test class for PaymentRepositoryImpl
   Author: Abdullahi (your student number)
   Date: 22 March 2026
*/
package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.PaymentMethod;
import za.ac.cput.domain.PaymentStatus;
import za.ac.cput.factory.PaymentFactory;
import za.ac.cput.repository.impl.PaymentRepositoryImpl;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {

    private PaymentRepositoryImpl repository;
    private Payment payment;

    @BeforeEach
    void setUp() {
        repository = PaymentRepositoryImpl.getInstance();
        payment = PaymentFactory.createPayment(
                1,
                750.00,
                LocalDateTime.of(2026, 3, 22, 9, 30),
                PaymentMethod.EFT,
                PaymentStatus.PAID,
                201
        );
    }

    @Test
    void testCreate() {
        Payment created = repository.create(payment);
        assertNotNull(created);
        assertEquals(payment.getPaymentId(), created.getPaymentId());
        System.out.println("Create test passed: " + created);
    }

    @Test
    void testRead() {
        repository.create(payment);
        Payment read = repository.read(1);
        assertNotNull(read);
        assertEquals(1, read.getPaymentId());
        System.out.println("Read test passed: " + read);
    }

    @Test
    void testUpdate() {
        repository.create(payment);
        Payment updatedPayment = PaymentFactory.createPayment(
                1,
                900.00,
                LocalDateTime.now(),
                PaymentMethod.MEDICAL_AID,
                PaymentStatus.PENDING,
                201
        );
        Payment result = repository.update(updatedPayment);
        assertNotNull(result);
        assertEquals(900.00, result.getPaymentAmount());
        assertEquals(PaymentMethod.MEDICAL_AID, result.getPaymentMethod());
        System.out.println("Update test passed: " + result);
    }

    @Test
    void testDelete() {
        repository.create(payment);
        boolean deleted = repository.delete(1);
        assertTrue(deleted);
        assertNull(repository.read(1));
        System.out.println("Delete test passed: payment successfully removed");
    }

    @Test
    void testDelete_NonExistent_ReturnsFalse() {
        boolean deleted = repository.delete(999);
        assertFalse(deleted);
        System.out.println("Delete non-existent test passed: correctly returned false");
    }
}
