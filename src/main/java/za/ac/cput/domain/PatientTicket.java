/* PatientTicket.java
   PatientTicket domain entity (stub for Payment integration)
   Full implementation by Joshua A
   Author: Abdullahi (230971091)
   Date: 25 March 2026
*/
package za.ac.cput.domain;

import java.time.LocalDateTime;

public class PatientTicket {
    private int ticketId;
    private String ticketDescription;
    private LocalDateTime ticketCreatedDate;

    private PatientTicket() {}

    private PatientTicket(Builder builder) {
        this.ticketId = builder.ticketId;
        this.ticketDescription = builder.ticketDescription;
        this.ticketCreatedDate = builder.ticketCreatedDate;
    }

    // Getters
    public int getTicketId() { return ticketId; }
    public String getTicketDescription() { return ticketDescription; }
    public LocalDateTime getTicketCreatedDate() { return ticketCreatedDate; }

    @Override
    public String toString() {
        return "PatientTicket{" +
                "ticketId=" + ticketId +
                ", ticketDescription='" + ticketDescription + '\'' +
                ", ticketCreatedDate=" + ticketCreatedDate +
                '}';
    }

    public static class Builder {
        private int ticketId;
        private String ticketDescription;
        private LocalDateTime ticketCreatedDate;

        public Builder setTicketId(int ticketId) {
            this.ticketId = ticketId;
            return this;
        }
        public Builder setTicketDescription(String ticketDescription) {
            this.ticketDescription = ticketDescription;
            return this;
        }
        public Builder setTicketCreatedDate(LocalDateTime ticketCreatedDate) {
            this.ticketCreatedDate = ticketCreatedDate;
            return this;
        }
        public PatientTicket build() {
            return new PatientTicket(this);
        }
    }
}
