/* AppointmentFactory.java
Appointment Model Class
Author: Joshua Peter Bonzet (221312536)
Date: 26 March 2026
*/

package za.ac.cput.factory;

import za.ac.cput.domain.Appointment;
import za.ac.cput.domain.ClinicStaff;
import za.ac.cput.domain.ConfirmationStatus;
import za.ac.cput.domain.Doctor;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentFactory {

    public static Appointment createAppointment(int appointmentId,
                                                LocalDate appointmentDate,
                                                LocalTime appointmentTime,
                                                ConfirmationStatus confirmationStatus,
                                                Doctor doctor,
                                                ClinicStaff staff) {

        if (appointmentId <= 0) return null;
        if (appointmentDate == null || appointmentTime == null) return null;
        if (confirmationStatus == null) return null;
        if (doctor == null || staff == null) return null;

        return new Appointment.Builder()
                .setAppointmentId(appointmentId)
                .setAppointmentDate(appointmentDate)
                .setAppointmentTime(appointmentTime)
                .setConfirmationStatus(confirmationStatus)
                .setDoctor(doctor)
                .setStaff(staff)
                .build();
    }
}
