/* AppointmentRepository.java
Appointment Model Class
Author: Joshua Peter Bonzet (221312536)
Date: 26 March 2026
*/

package za.ac.cput.repository.impl;

import za.ac.cput.domain.Appointment;
import za.ac.cput.repository.IAppointmentRepository;

import java.util.HashSet;
import java.util.Set;

public class AppointmentRepository implements IAppointmentRepository {

    private static AppointmentRepository repository = null;
    private final Set<Appointment> appointmentSet;

    private AppointmentRepository() {
        this.appointmentSet = new HashSet<>();
    }

    public static AppointmentRepository getRepository() {
        if (repository == null) {
            repository = new AppointmentRepository();
        }
        return repository;
    }

    @Override
    public Appointment create(Appointment appointment) {
        boolean added = this.appointmentSet.add(appointment);
        return added ? appointment : null;
    }

    @Override
    public Appointment read(Integer appointmentId) {
        for (Appointment appointment : this.appointmentSet) {
            if (appointment.getAppointmentId() == appointmentId) {
                return appointment;
            }
        }
        return null;
    }

    @Override
    public Appointment update(Appointment appointment) {
        for (Appointment a : this.appointmentSet) {
            if (a.getAppointmentId() == appointment.getAppointmentId()) {
                this.appointmentSet.remove(a);
                this.appointmentSet.add(appointment);
                return appointment;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Integer appointmentId) {
        for (Appointment appointment : this.appointmentSet) {
            if (appointment.getAppointmentId() == appointmentId) {
                this.appointmentSet.remove(appointment);
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<Appointment> getAll() {
        return new HashSet<>(this.appointmentSet);
    }
}
