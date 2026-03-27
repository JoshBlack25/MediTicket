/* PatientTicketFactory.java
   Author: Joshua A (230317693)
   Date: 21 March 2026
*/
package za.ac.cput.factory;

import za.ac.cput.domain.Appointment;
import za.ac.cput.domain.Patient;
import za.ac.cput.domain.PatientTicket;
import za.ac.cput.domain.enums.ConfirmationStatus;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

public class PatientTicketFactory {

    public static PatientTicket createTicket(int ticketId,
                                             String ticketDescription,
                                             Patient patient,
                                             Appointment appointment) {

        if (!Helper.isValidId(ticketId)) return null;
        if (Helper.isNullOrEmpty(ticketDescription)) return null;
        if (patient == null) return null;
        if (appointment == null) return null;
        if (appointment.getConfirmationStatus()
                != ConfirmationStatus.CONFIRMED) return null;

        return new PatientTicket.Builder()
                .setTicketId(ticketId)
                .setTicketDescription(ticketDescription)
                .setTicketCreatedDate(LocalDateTime.now())
                .setPatient(patient)
                .setAppointment(appointment)
                .build();
    }
}