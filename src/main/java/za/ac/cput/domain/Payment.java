/* Payment.java
   Payment domain entity using Builder Pattern
   Author: Abdullahi (your student number)
   Date: 22 March 2026
*/
package za.ac.cput.domain;

import java.time.LocalDateTime;

public class Payment {
    private int paymentId;
    private double paymentAmount;
    private LocalDateTime paymentDate;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private int ticketId; // references PatientTicket by ID to avoid tight coupling

    private Payment() {}

    private Payment(Builder builder) {
        this.paymentId = builder.paymentId;
        this.paymentAmount = builder.paymentAmount;
        this.paymentDate = builder.paymentDate;
        this.paymentMethod = builder.paymentMethod;
        this.paymentStatus = builder.paymentStatus;
        this.ticketId = builder.ticketId;
    }

    // Getters
    public int getPaymentId() { return paymentId; }
    public double getPaymentAmount() { return paymentAmount; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public int getTicketId() { return ticketId; }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", paymentAmount=" + paymentAmount +
                ", paymentDate=" + paymentDate +
                ", paymentMethod=" + paymentMethod +
                ", paymentStatus=" + paymentStatus +
                ", ticketId=" + ticketId +
                '}';
    }

    // Builder class
    public static class Builder {
        private int paymentId;
        private double paymentAmount;
        private LocalDateTime paymentDate;
        private PaymentMethod paymentMethod;
        private PaymentStatus paymentStatus;
        private int ticketId;

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
        public Builder setTicketId(int ticketId) {
            this.ticketId = ticketId;
            return this;
        }
        public Payment build() {
            return new Payment(this);
        }
    }

    // Main method to verify builder functionality
    public static void main(String[] args) {
        Payment payment = new Payment.Builder()
                .setPaymentId(1)
                .setPaymentAmount(750.00)
                .setPaymentDate(LocalDateTime.now())
                .setPaymentMethod(PaymentMethod.CARD)
                .setPaymentStatus(PaymentStatus.PAID)
                .setTicketId(101)
                .build();

        System.out.println("Payment created: " + payment);
    }
}
