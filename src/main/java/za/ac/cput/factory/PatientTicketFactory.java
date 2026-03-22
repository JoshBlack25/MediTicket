/* PatientTicketFactory.java
   Factory class for creating PatientTicket objects
   Author: Joshua A (230317693)
   Date: 21 March 2026
*/

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
