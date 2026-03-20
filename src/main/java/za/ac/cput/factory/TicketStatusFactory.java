package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.domain.enums.StatusType;

import java.time.LocalDateTime;

public class TicketStatusFactory {

    public static TicketStatus createStatus(int statusId, StatusType statusType, PatientTicket ticket){

        return new TicketStatus.Builder()
                .setStatusId(statusId)
                .setStatusType(statusType)
                .setStatusDate(LocalDateTime.now())
                .setPatient(ticket)
                .build();
    }

}
