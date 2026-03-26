/* TicketStatusFactory.java
   Factory class for creating TicketStatus objects
   Author: Joshua A (230317693)
   Date: 21 March 2026
*/

package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.domain.enums.StatusType;

import java.time.LocalDateTime;

public class TicketStatusFactory {

    public static TicketStatus createStatus(int statusId, StatusType statusType, PatientTicket ticket){

        if (statusId <= 0 || statusType == null || ticket == null) {
            return null;
        }

        return new TicketStatus.Builder()
                .setStatusId(statusId)
                .setStatusType(statusType)
                .setStatusDate(LocalDateTime.now())
                .setTicket(ticket)
                .build();
    }

}
