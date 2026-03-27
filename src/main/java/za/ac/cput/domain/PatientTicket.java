package za.ac.cput.domain;

import za.ac.cput.domain.enums.StatusType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PatientTicket {

    // Attributes
    private int ticketId;
    private String ticketDescription;
    private LocalDateTime ticketCreatedDate;
    private Patient patient;
    private Appointment appointment;
    private List<TicketStatus> statusHistory;

    // Constructor
    private PatientTicket(Builder builder){
        this.ticketId = builder.ticketId;
        this.ticketDescription = builder.ticketDescription;
        this.ticketCreatedDate = builder.ticketCreatedDate;
        this.patient = builder.patient;
        this.appointment = builder.appointment;
        this.statusHistory = new ArrayList<>(); // initialize history
    }

    // Getters
    public int getTicketId(){
        return ticketId;
    }

    public String getTicketDescription(){
        return ticketDescription;
    }

    public LocalDateTime getTicketCreatedDate(){
        return ticketCreatedDate;
    }

    public Patient getPatient(){
        return patient;
    }

    public Appointment getAppointment(){
        return appointment;
    }

    public List<TicketStatus> getStatusHistory(){
        return statusHistory;
    }

    // Business Logic (THIS is where status is handled correctly)
    public void addStatus(StatusType statusType){
        TicketStatus status = new TicketStatus.Builder()
                .setStatusType(statusType)
                .setStatusDate(LocalDateTime.now())
                .setTicket(this)
                .build();

        this.statusHistory.add(status);
    }

    // Optional: Get latest status (clean design)
    public StatusType getCurrentStatus(){
        if(statusHistory.isEmpty()){
            return null;
        }
        return statusHistory.get(statusHistory.size() - 1).getStatusType();
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

    // Builder Class
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

        public Builder copy(PatientTicket ticket) {
            this.ticketId = ticket.ticketId;
            this.ticketDescription = ticket.ticketDescription;
            this.ticketCreatedDate = ticket.ticketCreatedDate;
            this.patient = ticket.patient;
            this.appointment = ticket.appointment;
            return this;
        }

        public PatientTicket build(){
            return new PatientTicket(this);
        }
    }
}