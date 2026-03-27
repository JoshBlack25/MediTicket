/* TicketStatusFactory.java
   Author: Joshua A (230317693)
   Date: 21 March 2026
*/
package za.ac.cput.factory;

import za.ac.cput.domain.PatientTicket;
import za.ac.cput.domain.TicketStatus;
import za.ac.cput.domain.enums.StatusType;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

public class TicketStatusFactory {

    public static TicketStatus createStatus(int statusId,
                                            StatusType statusType,
                                            PatientTicket ticket) {

        if (!Helper.isValidId(statusId)) return null;
        if (statusType == null) return null;
        if (ticket == null) return null;

        return new TicketStatus.Builder()
                .setStatusId(statusId)
                .setStatusType(statusType)
                .setStatusDate(LocalDateTime.now())
                .setTicket(ticket)
                .build();
    }
}