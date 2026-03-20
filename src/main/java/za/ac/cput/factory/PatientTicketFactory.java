package za.ac.cput.factory;

import za.ac.cput.domain.*;

public class PatientTicketFactory {

    public static PatientTicket createTicket(int ticketId, String ticketDescription, Patient patient){

        return  new PatientTicket.Builder()
                .setTicketId(ticketId)
                .setTicketDescription(ticketDescription)
                .setPatient(patient)
                .build();
    }
}
