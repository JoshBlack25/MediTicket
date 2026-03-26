/* PatientTicketFactory.java
   Factory class for creating PatientTicket objects
   Author: Joshua A (230317693)
   Date: 21 March 2026
*/

package za.ac.cput.factory;

import za.ac.cput.domain.*;

import java.time.LocalDateTime;

public class PatientTicketFactory {

    public static PatientTicket createTicket(int ticketId, String ticketDescription, Patient patient, Appointment appointment){

        if (ticketId <= 0 ||
                ticketDescription == null || ticketDescription.isEmpty() ||
                patient == null ||
                appointment == null) {
            return null;
        }

        return  new PatientTicket.Builder()
                .setTicketId(ticketId)
                .setTicketDescription(ticketDescription)
                .setTicketCreatedDate(LocalDateTime.now())
                .setPatient(patient)
                .setAppointment(appointment)
                .build();
    }
}
