/* Payment.java
   Payment domain entity using Builder Pattern
   Author: Abdullahi (230971091)
   Date: 22 March 2026
*/
package za.ac.cput.domain;

import za.ac.cput.domain.enums.PaymentMethod;
import za.ac.cput.domain.enums.PaymentStatus;

import java.time.LocalDateTime;

public class Payment {
    private int paymentId;
    private double paymentAmount;
    private LocalDateTime paymentDate;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private PatientTicket ticket;

    private Payment() {
    }

    private Payment(Builder builder) {
        this.paymentId = builder.paymentId;
        this.paymentAmount = builder.paymentAmount;
        this.paymentDate = builder.paymentDate;
        this.paymentMethod = builder.paymentMethod;
        this.paymentStatus = builder.paymentStatus;
        this.ticket = builder.ticket;
    }

    // Getters
    public int getPaymentId() {
        return paymentId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public PatientTicket getTicket() {
        return ticket;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", paymentAmount=" + paymentAmount +
                ", paymentDate=" + paymentDate +
                ", paymentMethod=" + paymentMethod +
                ", paymentStatus=" + paymentStatus +
                ", ticket=" + ticket +
                '}';
    }

    // Builder class
    public static class Builder {
        private int paymentId;
        private double paymentAmount;
        private LocalDateTime paymentDate;
        private PaymentMethod paymentMethod;
        private PaymentStatus paymentStatus;
        private PatientTicket ticket;

        public Builder setPaymentId(int paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder setPaymentAmount(double paymentAmount) {
            this.paymentAmount = paymentAmount;
            return this;
        }

        public Builder setPaymentDate(LocalDateTime paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder setPaymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder setPaymentStatus(PaymentStatus paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public Builder setTicket(PatientTicket ticket) {
            this.ticket = ticket;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }

    // Main method to verify builder functionality
    public static void main(String[] args) {
        PatientTicket ticket = new PatientTicket.Builder()
                .setTicketId(101)
                .setTicketDescription("General checkup")
                .setTicketCreatedDate(LocalDateTime.now())
                .build();

        Payment payment = new Payment.Builder()
                .setPaymentId(1)
                .setPaymentAmount(750.00)
                .setPaymentDate(LocalDateTime.now())
                .setPaymentMethod(PaymentMethod.CARD)
                .setPaymentStatus(PaymentStatus.PAID)
                .setTicket(ticket)
                .build();

        System.out.println("Payment created: " + payment);
    }
}
