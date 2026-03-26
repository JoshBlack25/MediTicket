/* TicketStatus.java
   Domain class for ticket status using Builder Pattern
   Author: Joshua A (230317693)
   Date: 20 March 2026
*/

package za.ac.cput.domain;

import za.ac.cput.domain.enums.StatusType;

import java.time.LocalDateTime;

public class TicketStatus {

    //Attributes
    private int statusId;
    private StatusType statusType;
    private LocalDateTime statusDate;
    private PatientTicket ticket;

    //Getters
    public int getStatusId(){
        return statusId;
    }

    public StatusType getStatusType(){
        return statusType;
    }

    public LocalDateTime getStatusDate(){
        return statusDate;
    }

    public PatientTicket getTicket(){
        return ticket;
    }

    //Methods
    public boolean isEscalated(){
        return this.statusType == StatusType.ESCALATED;
    }

    public boolean isClosed(){
        return this.statusType == StatusType.CLOSED;
    }

    @Override
    public String toString() {
        return "TicketStatus{" +
                "statusId=" + statusId +
                ", statusType=" + statusType +
                ", statusDate=" + statusDate +
                ", ticket=" + ticket +
                '}';
    }

    private TicketStatus(Builder builder){
        this.statusId = builder.statusId;
        this.statusType = builder.statusType;
        this.statusDate = builder.statusDate;
        this.ticket = builder.ticket;
    }

    public static class Builder{
        private int statusId;
        private StatusType statusType;
        private LocalDateTime statusDate;
        private PatientTicket ticket;

        public Builder setStatusId(int statusId){
            this.statusId = statusId;
            return this;
        }

        public Builder setStatusType(StatusType statusType){
            this.statusType = statusType;
            return this;
        }

        public Builder setStatusDate(LocalDateTime statusDate){
            this.statusDate = statusDate;
            return this;
        }

        public Builder setTicket(PatientTicket ticket){
            this.ticket = ticket;
            return this;
        }

        public TicketStatus build(){
            return new TicketStatus(this);
        }
    }
}
