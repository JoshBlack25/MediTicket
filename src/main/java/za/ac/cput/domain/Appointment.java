/* Appointment.java
Appointment Model Class
Author: Joshua Peter Bonzet (221312536)
Date: 26 March 2026
*/
package za.ac.cput.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private final int appointmentId;
    private final LocalDate appointmentDate;
    private final LocalTime appointmentTime;
    private ConfirmationStatus confirmationStatus;
    private Doctor doctor;
    private ClinicStaff staff;

    public Appointment(Builder builder) {
        this.appointmentId = builder.appointmentId;
        this.appointmentDate = builder.appointmentDate;
        this.appointmentTime = builder.appointmentTime;
        this.confirmationStatus = builder.confirmationStatus;
        this.doctor = builder.doctor;
        this.staff = builder.staff;
    }

    //Getters
    public int getAppointmentId() {return appointmentId;}
    public LocalDate getAppointmentDate() {return appointmentDate;}
    public LocalTime getAppointmentTime() {return appointmentTime;}
    public ConfirmationStatus getConfirmationStatus() {return confirmationStatus;}
    public Doctor getDoctor() {return doctor;}
    public ClinicStaff getStaff() {return staff;}

    @Override
    public String toString() {
        return "===Appointments===\n" +
                "Appointment ID: " + appointmentId + "\n" +
                "Appointment Date: " + appointmentDate + "\n" +
                "Appointment Time: " + appointmentTime + "\n" +
                "Status: " + confirmationStatus + "\n" +
                doctor + "\n" +
                staff + "\n";
    }

    public static class Builder{
        private int appointmentId;
        private LocalDate appointmentDate;
        private LocalTime appointmentTime;
        private ConfirmationStatus confirmationStatus;
        private Doctor doctor;
        private ClinicStaff staff;

        public Builder setAppointmentId(int appointmentId) {
            this.appointmentId = appointmentId;
            return this;
        }
        public Builder setAppointmentDate(LocalDate appointmentDate) {
            this.appointmentDate = appointmentDate;
            return this;
        }
        public Builder setAppointmentTime(LocalTime appointmentTime) {
            this.appointmentTime = appointmentTime;
            return this;
        }
        public Builder setConfirmationStatus(ConfirmationStatus confirmationStatus) {
            this.confirmationStatus = confirmationStatus;
            return this;
        }
        public Builder setDoctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }
        public Builder setStaff(ClinicStaff staff) {
            this.staff = staff;
            return this;
        }
        public Appointment build() {
            return new Appointment(this);
        }
    }
}
