/* PatientTicket.java
   Domain class for patient ticket using Builder Pattern
   Author: Joshua A (230317693)
   Date: 20 March 2026
*/

package za.ac.cput.domain;

import za.ac.cput.domain.enums.StatusType;

import java.time.LocalDateTime;

public class PatientTicket {

    //Attributes
    private int ticketId;
    private String ticketDescription;
    private LocalDateTime ticketCreatedDate;
    private StatusType currentStatus;
    private Patient patient;
    private Appointment appointment;

    //Getters
    public int getTicketId(){
        return ticketId;
    }

    public String getTicketDescription(){
        return ticketDescription;
    }

    public LocalDateTime getTicketCreatedDate(){
        return ticketCreatedDate;
    }

    public StatusType getCurrentStatus(){
        return currentStatus;
    }

    public Patient getPatient(){
        return patient;
    }

    public Appointment getAppointment(){
        return appointment;
    }

    //Methods
    public void openTicket(){
        this.ticketCreatedDate = LocalDateTime.now();
        this.currentStatus = StatusType.OPEN;
    }

    public void closeTicket(){
        this.currentStatus = StatusType.CLOSED;
    }

    public void assignAppointment(Appointment appointment){
        this.appointment = appointment;
    }

    @Override
    public String toString() {
        return "PatientTicket{" +
                "ticketId=" + ticketId +
                ", ticketDescription='" + ticketDescription + '\'' +
                ", ticketCreatedDate=" + ticketCreatedDate +
                ", patient=" + patient +
                ", appointment=" + appointment +
                '}';
    }

    //Constructor
    private PatientTicket(Builder builder){
        this.ticketId = builder.ticketId;
        this.ticketDescription = builder.ticketDescription;
        this.ticketCreatedDate = builder.ticketCreatedDate;
        this.patient = builder.patient;
        this.appointment = builder.appointment;
    }

    //Builder Class
    public static class Builder{
        private int ticketId;
        private String ticketDescription;
        private LocalDateTime ticketCreatedDate;
        private Patient patient;
        private Appointment appointment;

        public Builder setTicketId(int ticketId){
            this.ticketId = ticketId;
            return this;
        }

        public Builder setTicketDescription(String ticketDescription){
            this.ticketDescription = ticketDescription;
            return this;
        }

        public Builder setTicketCreatedDate(LocalDateTime ticketCreatedDate){
            this.ticketCreatedDate = ticketCreatedDate;
            return this;
        }

        public Builder setPatient(Patient patient){
            this.patient = patient;
            return this;
        }

        public Builder setAppointment(Appointment appointment){
            this.appointment = appointment;
            return this;
        }

        public PatientTicket build(){
            return new PatientTicket(this);
        }
    }
}