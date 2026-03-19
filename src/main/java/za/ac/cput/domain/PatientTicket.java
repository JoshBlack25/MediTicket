package za.ac.cput.domain;

import java.time.LocalDateTime;

public class PatientTicket {

    private int ticketId;
    private String ticketDescription;
    private LocalDateTime ticketCreatedDate;
    private Patient patient;
    private Appointment appointment;

    public int getTicketId(){
        return ticketId;
    }

    public String getTicketDescription(){
        return ticketDescription;
    }

    public LocalDateTime getTicketCreatedDate(){
        return ticketCreatedDate;
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

    private PatientTicket(Builder builder){
        this.ticketId = builder.ticketId;
        this.ticketDescription = builder.ticketDescription;
        this.ticketCreatedDate = builder.ticketCreatedDate;
        this.patient = builder.patient;
        this.appointment = builder.appointment;
    }

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