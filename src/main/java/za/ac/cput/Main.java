package za.ac.cput;

import za.ac.cput.domain.Appointment;
import za.ac.cput.domain.Patient;
import za.ac.cput.domain.PatientTicket;
import za.ac.cput.domain.TicketStatus;
import za.ac.cput.domain.enums.StatusType;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        Patient patient = new Patient();
        Appointment appointment = new Appointment();

        // --- Build PatientTicket ---
        PatientTicket ticket = new PatientTicket.Builder()
                .setTicketId(1)
                .setTicketDescription("Patient requesting prescription refill")
                .setPatient(patient)
                .build();

        System.out.println("=== After build ===");
        System.out.println(ticket);
        System.out.println("Current Status : " + ticket.getCurrentStatus());

        // --- openTicket() ---
        ticket.openTicket();
        System.out.println("\n=== After openTicket() ===");
        System.out.println(ticket);
        System.out.println("Ticket ID      : " + ticket.getTicketId());
        System.out.println("Description    : " + ticket.getTicketDescription());
        System.out.println("Created Date   : " + ticket.getTicketCreatedDate());
        System.out.println("Current Status : " + ticket.getCurrentStatus());

        // --- assignAppointment() ---
        ticket.assignAppointment(appointment);
        System.out.println("\n=== After assignAppointment() ===");
        System.out.println(ticket);

        // --- closeTicket() ---
        ticket.closeTicket();
        System.out.println("\n=== After closeTicket() ===");
        System.out.println(ticket);
        System.out.println("Current Status : " + ticket.getCurrentStatus());

        // --- Build TicketStatus (CLOSED) ---
        TicketStatus closedStatus = new TicketStatus.Builder()
                .setStatusId(1)
                .setStatusType(StatusType.CLOSED)
                .setStatusDate(LocalDateTime.now())
                .setPatient(ticket)
                .build();

        System.out.println("\n=== TicketStatus - CLOSED ===");
        System.out.println(closedStatus);
        System.out.println("isClosed()    : " + closedStatus.isClosed());
        System.out.println("isEscalated() : " + closedStatus.isEscalated());

        // --- Build TicketStatus (ESCALATED) ---
        TicketStatus escalatedStatus = new TicketStatus.Builder()
                .setStatusId(2)
                .setStatusType(StatusType.ESCALATED)
                .setStatusDate(LocalDateTime.now())
                .setPatient(ticket)
                .build();

        System.out.println("\n=== TicketStatus - ESCALATED ===");
        System.out.println(escalatedStatus);
        System.out.println("isEscalated() : " + escalatedStatus.isEscalated());
        System.out.println("isClosed()    : " + escalatedStatus.isClosed());
    }
}