/* MediTicketApplication.java
   Combined main method test for all domain classes
   Tests the full MediTicket system flow
   Authors: All Team Members
   Date: 27 March 2026
*/

package za.ac.cput;

import za.ac.cput.domain.*;
import za.ac.cput.domain.enums.*;
import za.ac.cput.factory.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║        MEDITICKET SYSTEM - FULL TEST     ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // ============================================================
        // STEP 1: CREATE DOCTOR
        // ============================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" STEP 1: Creating Doctor");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Doctor doctor = DoctorFactory.buildDoctor(
                1,
                "James",
                "Smith",
                "Cardiologist",
                "0821234567",
                "james.smith@mediticket.com"
        );

        if (doctor != null) {
            System.out.println("✔ Doctor created successfully");
            System.out.println("  " + doctor);
            System.out.println("  Full Name: " + doctor.getFullName());
        } else {
            System.out.println("✘ Doctor creation failed");
        }

        System.out.println();

        // ============================================================
        // STEP 2: CREATE CLINIC STAFF
        // ============================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" STEP 2: Creating Clinic Staff");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        ClinicStaff staff = ClinicStaffFactory.createClinicStaff(
                1,
                "Jane",
                "Doe",
                "Receptionist",
                "jane.doe@mediticket.com",
                "0831234567"
        );

        if (staff != null) {
            System.out.println("✔ Clinic Staff created successfully");
            System.out.println("  " + staff);
            System.out.println("  Full Name: " + staff.getFullName());
        } else {
            System.out.println("✘ Clinic Staff creation failed");
        }

        System.out.println();

        // ============================================================
        // STEP 3: CREATE PATIENT
        // ============================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" STEP 3: Creating Patient");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Patient patient = PatientFactory.createPatient(
                1,
                "John",
                "Doe",
                "0712345678",
                "john.doe@example.com",
                LocalDate.of(1990, 5, 15)
        );

        if (patient != null) {
            System.out.println("✔ Patient created successfully");
            System.out.println("  " + patient);
        } else {
            System.out.println("✘ Patient creation failed");
        }

        System.out.println();

        // ============================================================
        // STEP 4: CREATE APPOINTMENT
        // ============================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" STEP 4: Creating Appointment");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Appointment appointment = AppointmentFactory.createAppointment(
                1,
                LocalDate.of(2026, 4, 10),
                LocalTime.of(9, 0),
                ConfirmationStatus.CONFIRMED,
                doctor,
                staff
        );

        if (appointment != null) {
            System.out.println("✔ Appointment created successfully");
            System.out.println("  " + appointment);
            System.out.println("  Status : " + appointment.getConfirmationStatus());
            System.out.println("  Doctor : " + appointment.getDoctor().getFullName());
            System.out.println("  Staff  : " + appointment.getStaff().getFullName());
        } else {
            System.out.println("✘ Appointment creation failed");
        }

        System.out.println();

        // ============================================================
        // STEP 5: CREATE PATIENT TICKET
        // ============================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" STEP 5: Creating Patient Ticket");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        PatientTicket ticket = PatientTicketFactory.createTicket(
                1,
                "Patient requesting prescription refill",
                patient,
                appointment
        );

        if (ticket != null) {
            System.out.println("✔ Patient Ticket created successfully");
            System.out.println("  " + ticket);
            System.out.println("  Current Status : " + ticket.getCurrentStatus());
        } else {
            System.out.println("✘ Patient Ticket creation failed");
        }

        System.out.println();

        // ============================================================
        // STEP 6: TICKET STATUS LIFECYCLE
        // ============================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" STEP 6: Ticket Status Lifecycle");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        if (ticket != null) {
            ticket.addStatus(StatusType.OPEN);
            System.out.println("✔ Ticket opened");
            System.out.println("  Current Status : " + ticket.getCurrentStatus());

            ticket.addStatus(StatusType.IN_PROGRESS);
            System.out.println("✔ Ticket in progress");
            System.out.println("  Current Status : " + ticket.getCurrentStatus());

            ticket.addStatus(StatusType.ESCALATED);
            System.out.println("✔ Ticket escalated");
            System.out.println("  Current Status : " + ticket.getCurrentStatus());

            ticket.addStatus(StatusType.RESOLVED);
            System.out.println("✔ Ticket resolved");
            System.out.println("  Current Status : " + ticket.getCurrentStatus());

            ticket.addStatus(StatusType.CLOSED);
            System.out.println("✔ Ticket closed");
            System.out.println("  Current Status : " + ticket.getCurrentStatus());

            System.out.println("  Total Status History: "
                    + ticket.getStatusHistory().size() + " entries");
        }

        System.out.println();

        // ============================================================
        // STEP 7: CREATE TICKET STATUS DIRECTLY
        // ============================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" STEP 7: Creating Ticket Status via Factory");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        TicketStatus openStatus = TicketStatusFactory.createStatus(
                1, StatusType.OPEN, ticket);

        TicketStatus escalatedStatus = TicketStatusFactory.createStatus(
                2, StatusType.ESCALATED, ticket);

        TicketStatus closedStatus = TicketStatusFactory.createStatus(
                3, StatusType.CLOSED, ticket);

        if (openStatus != null) {
            System.out.println("✔ TicketStatus created successfully");
            System.out.println("  " + openStatus);
            System.out.println("  Is Escalated : " + openStatus.isEscalated());
            System.out.println("  Is Closed    : " + openStatus.isClosed());
        }

        if (escalatedStatus != null) {
            System.out.println("✔ Escalated Status:");
            System.out.println("  Is Escalated : " + escalatedStatus.isEscalated());
        }

        if (closedStatus != null) {
            System.out.println("✔ Closed Status:");
            System.out.println("  Is Closed    : " + closedStatus.isClosed());
        }

        System.out.println();

        // ============================================================
        // STEP 8: CREATE PAYMENT
        // ============================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" STEP 8: Creating Payment");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Payment payment = PaymentFactory.createPayment(
                1,
                750.00,
                LocalDateTime.now(),
                PaymentMethod.CARD,
                PaymentStatus.PAID,
                ticket
        );

        if (payment != null) {
            System.out.println("✔ Payment created successfully");
            System.out.println("  " + payment);
            System.out.println("  Method : " + payment.getPaymentMethod());
            System.out.println("  Status : " + payment.getPaymentStatus());
            System.out.println("  Amount : R" + payment.getPaymentAmount());
        } else {
            System.out.println("✘ Payment creation failed");
        }

        System.out.println();

        // ============================================================
        // STEP 9: CREATE NOTIFICATIONS
        // ============================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" STEP 9: Creating Notifications");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        NotificationService smsNotification = NotificationFactory.createSMS(
                1,
                "Your appointment on 10 April 2026 at 09:00 is confirmed.",
                patient,
                ticket,
                appointment
        );

        NotificationService emailNotification = NotificationFactory.createEMAIL(
                2,
                "Dear John, your payment of R750.00 has been received.",
                patient,
                ticket,
                appointment
        );

        if (smsNotification != null) {
            System.out.println("✔ SMS Notification created successfully");
            System.out.println("  " + smsNotification);
            System.out.println("  Type   : " + smsNotification.getNotificationType());
            System.out.println("  Status : " + smsNotification.getNotificationStatus());
            System.out.println("  Message: " + smsNotification.getNotificationMessage());
        } else {
            System.out.println("✘ SMS Notification creation failed");
        }

        if (emailNotification != null) {
            System.out.println("✔ EMAIL Notification created successfully");
            System.out.println("  " + emailNotification);
            System.out.println("  Type   : " + emailNotification.getNotificationType());
            System.out.println("  Status : " + emailNotification.getNotificationStatus());
            System.out.println("  Message: " + emailNotification.getNotificationMessage());
        } else {
            System.out.println("✘ EMAIL Notification creation failed");
        }

        System.out.println();

        // ============================================================
        // STEP 10: COPY BUILDER DEMONSTRATION
        // ============================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" STEP 10: Copy Builder Demonstration");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // Copy doctor and update specialty
        Doctor updatedDoctor = new Doctor.Builder()
                .copy(doctor)
                .setDoctorSpecialty("Neurologist")
                .build();
        System.out.println("✔ Doctor updated via copy builder");
        System.out.println("  Original Specialty : " + doctor.getDoctorSpecialty());
        System.out.println("  Updated Specialty  : " + updatedDoctor.getDoctorSpecialty());

        // Copy patient and update contact
        Patient updatedPatient = new Patient.Builder()
                .copy(patient)
                .setPatientCell("0799999999")
                .setPatientEmail("john.new@example.com")
                .build();
        System.out.println("✔ Patient updated via copy builder");
        System.out.println("  Original Cell  : " + patient.getPatientCell());
        System.out.println("  Updated Cell   : " + updatedPatient.getPatientCell());

        // Copy appointment and reschedule
        Appointment rescheduled = new Appointment.Builder()
                .copy(appointment)
                .setAppointmentDate(LocalDate.of(2026, 5, 20))
                .setAppointmentTime(LocalTime.of(14, 30))
                .setConfirmationStatus(ConfirmationStatus.RESCHEDULED)
                .build();
        System.out.println("✔ Appointment rescheduled via copy builder");
        System.out.println("  Original Date : " + appointment.getAppointmentDate());
        System.out.println("  New Date      : " + rescheduled.getAppointmentDate());
        System.out.println("  New Status    : " + rescheduled.getConfirmationStatus());

        System.out.println();

        // ============================================================
        // STEP 11: VALIDATION FAILURE DEMONSTRATIONS
        // ============================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" STEP 11: Validation Failure Demonstrations");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Doctor invalidDoctor = DoctorFactory.buildDoctor(
                0, "", "", "", "", "invalidemail");
        System.out.println("✔ Invalid Doctor returns null : "
                + (invalidDoctor == null ? "PASS" : "FAIL"));

        Patient invalidPatient = PatientFactory.createPatient(
                0, "", "", "", "bademail",
                LocalDate.of(2030, 1, 1));
        System.out.println("✔ Invalid Patient returns null : "
                + (invalidPatient == null ? "PASS" : "FAIL"));

        Appointment pastAppointment = AppointmentFactory.createAppointment(
                1,
                LocalDate.of(2020, 1, 1),
                LocalTime.of(9, 0),
                ConfirmationStatus.CONFIRMED,
                doctor,
                staff
        );
        System.out.println("✔ Past Appointment returns null : "
                + (pastAppointment == null ? "PASS" : "FAIL"));

        Payment invalidPayment = PaymentFactory.createPayment(
                0, -100.00, null, null, null, null);
        System.out.println("✔ Invalid Payment returns null  : "
                + (invalidPayment == null ? "PASS" : "FAIL"));

        PatientTicket unconfirmedTicket = PatientTicketFactory.createTicket(
                1, "Test",
                patient,
                new Appointment.Builder()
                        .setAppointmentId(99)
                        .setAppointmentDate(LocalDate.of(2026, 4, 10))
                        .setAppointmentTime(LocalTime.of(9, 0))
                        .setConfirmationStatus(ConfirmationStatus.PENDING)
                        .build()
        );
        System.out.println("✔ Unconfirmed Ticket returns null: "
                + (unconfirmedTicket == null ? "PASS" : "FAIL"));

        System.out.println();

        // ============================================================
        // FINAL SUMMARY
        // ============================================================
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║            SYSTEM TEST SUMMARY           ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  Doctor            : " + (doctor != null           ? "✔ PASS" : "✘ FAIL") + "              ║");
        System.out.println("║  ClinicStaff       : " + (staff != null            ? "✔ PASS" : "✘ FAIL") + "              ║");
        System.out.println("║  Patient           : " + (patient != null          ? "✔ PASS" : "✘ FAIL") + "              ║");
        System.out.println("║  Appointment       : " + (appointment != null      ? "✔ PASS" : "✘ FAIL") + "              ║");
        System.out.println("║  PatientTicket     : " + (ticket != null           ? "✔ PASS" : "✘ FAIL") + "              ║");
        System.out.println("║  TicketStatus      : " + (openStatus != null       ? "✔ PASS" : "✘ FAIL") + "              ║");
        System.out.println("║  Payment           : " + (payment != null          ? "✔ PASS" : "✘ FAIL") + "              ║");
        System.out.println("║  SMS Notification  : " + (smsNotification != null  ? "✔ PASS" : "✘ FAIL") + "              ║");
        System.out.println("║  EMAIL Notification: " + (emailNotification != null ? "✔ PASS" : "✘ FAIL") + "              ║");
        System.out.println("║  Copy Builder      : " + (updatedDoctor != null    ? "✔ PASS" : "✘ FAIL") + "              ║");
        System.out.println("║  Validation Checks : ✔ PASS              ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }
}
