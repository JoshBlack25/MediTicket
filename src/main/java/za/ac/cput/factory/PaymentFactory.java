/* PaymentFactory.java
   Factory class for creating Payment objects
   Author: Abdullahi (230971091)
   Date: 25 March 2026
*/
package za.ac.cput.factory;

import za.ac.cput.domain.Payment;
import za.ac.cput.domain.enums.PaymentMethod;
import za.ac.cput.domain.enums.PaymentStatus;
import za.ac.cput.domain.PatientTicket;

import java.time.LocalDateTime;

public class PaymentFactory {

    public static Payment createPayment(int paymentId,
                                        double paymentAmount,
                                        LocalDateTime paymentDate,
                                        PaymentMethod paymentMethod,
                                        PaymentStatus paymentStatus,
                                        PatientTicket ticket) {
        if (paymentAmount <= 0) return null;
        if (paymentMethod == null) return null;
        if (paymentStatus == null) return null;

        return new Payment.Builder()
                .setPaymentId(paymentId)
                .setPaymentAmount(paymentAmount)
                .setPaymentDate(paymentDate != null ? paymentDate : LocalDateTime.now())
                .setPaymentMethod(paymentMethod)
                .setPaymentStatus(paymentStatus)
                .setTicket(ticket)
                .build();
    }
}