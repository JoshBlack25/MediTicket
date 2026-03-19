package za.ac.cput;

import za.ac.cput.domain.*;
import za.ac.cput.domain.enums.StatusType;

import java.time.LocalDateTime;

public class Main {
    public static void main() {

        //  --- Builder supporting objects ---
        Patient patient = new Patient();
        Appointment appointment = new Appointment();

        //  --- Builder PatientTicket ---
        PatientTicket ticket = new PatientTicket.Builder()
                .setTicketId(1)
                .setTicketDescription("Patient requesting prescription refill")
                .setTicketCreatedDate(LocalDateTime.of(2025, 3, 19, 9, 30))
                .setPatient(patient)
                .setAppointment(appointment)
                .build();

        System.out.println("=== PatientTiecket ====");
        System.out.println(ticket);
        System.out.println("Ticket ID: "+ticket.getTicketId());
        System.out.println("Description: "+ticket.getTicketDescription());
        System.out.println("Created Date: "+ticket.getTicketCreatedDate());

        System.out.println();

        //  --- Build TicketStatus ---
        TicketStatus status = new TicketStatus.Builder()
                .setStatusId(101)
                .setStatusType(StatusType.OPEN)
                .setStatusDate(LocalDateTime.of(2025, 3, 19, 9, 35))
                .setPatient(ticket)
                .build();

        System.out.println("=== TicketStatus ===");
        System.out.println(status);
        System.out.println("Status ID: "+status.getStatusId());
        System.out.println("Status Type: "+status.getStatusType());
        System.out.println("Status Date: "+status.getStatusDate());

        System.out.println();

        //  --- Test with a Different StatusType ---
        TicketStatus escalatedStatus = new TicketStatus.Builder()
                .setStatusId(102)
                .setStatusType(StatusType.ESCALATED)
                .setStatusDate(LocalDateTime.of(2025, 3, 19, 14, 0))
                .setPatient(ticket)
                .build();

        System.out.println("=== Escalated TicketStatus ===");
        System.out.println(escalatedStatus);
    }
}
