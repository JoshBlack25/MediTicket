package za.ac.cput.factory;

import za.ac.cput.domain.NotificationService;
import za.ac.cput.domain.Patient;
import za.ac.cput.domain.PatientTicket;
import za.ac.cput.domain.Appointment;
import za.ac.cput.domain.enums.NotificationType;
import za.ac.cput.domain.enums.NotificationStatus;
import java.time.LocalDateTime;

public class NotificationFactory {

    public static NotificationService createSMS(int notificationId, Patient patient, PatientTicket ticket, Appointment appointment) {
        return new NotificationService.Builder()
                .setNotificationId(notificationId)
                .setNotificationType(NotificationType.SMS)
                .setNotificationStatus(NotificationStatus.PENDING)
                .setNotificationDate(LocalDateTime.now())
                .setPatient(patient)
                .setTicket(ticket)
                .setAppointment(appointment)
                .build();
    }

    public static NotificationService createEMAIL(int notificationId, Patient patient, PatientTicket ticket, Appointment appointment) {
        return new NotificationService.Builder()
                .setNotificationId(notificationId)
                .setNotificationType(NotificationType.EMAIL)
                .setNotificationStatus(NotificationStatus.PENDING)
                .setNotificationDate(LocalDateTime.now())
                .setPatient(patient)
                .setTicket(ticket)
                .setAppointment(appointment)
                .build();
    }
}
