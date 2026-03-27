package za.ac.cput.factory;

import za.ac.cput.domain.Appointment;
import za.ac.cput.domain.NotificationService;
import za.ac.cput.domain.Patient;
import za.ac.cput.domain.PatientTicket;
import za.ac.cput.domain.enums.NotificationStatus;
import za.ac.cput.domain.enums.NotificationType;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

// Raul Everts 230270565
public class NotificationFactory {

    private static NotificationService buildNotification(int notificationId,
                                                         String message,
                                                         NotificationType type,
                                                         Patient patient,
                                                         PatientTicket ticket,
                                                         Appointment appointment) {
        if (!Helper.isValidId(notificationId)) return null;
        if (Helper.isNullOrEmpty(message)) return null;
        if (patient == null) return null;
        if (ticket == null) return null;
        if (appointment == null) return null;

        return new NotificationService.Builder()
                .setNotificationId(notificationId)
                .setNotificationType(type)
                .setNotificationStatus(NotificationStatus.PENDING)
                .setNotificationMessage(message)
                .setNotificationDate(LocalDateTime.now())
                .setPatient(patient)
                .setTicket(ticket)
                .setAppointment(appointment)
                .build();
    }

    public static NotificationService createSMS(int notificationId,
                                                String message,
                                                Patient patient,
                                                PatientTicket ticket,
                                                Appointment appointment) {
        return buildNotification(notificationId, message,
                NotificationType.SMS, patient, ticket, appointment);
    }

    public static NotificationService createEMAIL(int notificationId,
                                                  String message,
                                                  Patient patient,
                                                  PatientTicket ticket,
                                                  Appointment appointment) {
        return buildNotification(notificationId, message,
                NotificationType.EMAIL, patient, ticket, appointment);
    }
}