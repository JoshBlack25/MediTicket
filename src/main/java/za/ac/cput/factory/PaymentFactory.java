/* PaymentFactory.java
   Factory class for creating Payment objects
   Author: Abdullahi (your student number)
   Date: 22 March 2026
*/
package za.ac.cput.factory;

import za.ac.cput.domain.Payment;
import za.ac.cput.domain.PaymentMethod;
import za.ac.cput.domain.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentFactory {

    public static Payment createPayment(int paymentId,
                                        double paymentAmount,
                                        LocalDateTime paymentDate,
                                        PaymentMethod paymentMethod,
                                        PaymentStatus paymentStatus,
                                        int ticketId) {
        // Validation
        if (paymentAmount <= 0) return null;
        if (paymentMethod == null) return null;
        if (paymentStatus == null) return null;

        return new Payment.Builder()
                .setPaymentId(paymentId)
                .setPaymentAmount(paymentAmount)
                .setPaymentDate(paymentDate != null ? paymentDate : LocalDateTime.now())
                .setPaymentMethod(paymentMethod)
                .setPaymentStatus(paymentStatus)
                .setTicketId(ticketId)
                .build();
    }
}
