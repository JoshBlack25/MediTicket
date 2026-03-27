package za.ac.cput.repository.impl;

import za.ac.cput.domain.Appointment;
import za.ac.cput.repository.IAppointmentRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class AppointmentRepositoryImpl implements IAppointmentRepository {

    private static AppointmentRepositoryImpl repository = null;
    private final Set<Appointment> appointmentSet;

    private AppointmentRepositoryImpl() {
        this.appointmentSet = new HashSet<>();
    }

    public static AppointmentRepositoryImpl getRepository() {
        if (repository == null) {
            repository = new AppointmentRepositoryImpl();
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
            if (appointment.getAppointmentId() == appointmentId.intValue()) {
                return appointment;
            }
        }
        return null;
    }

    @Override
    public Appointment update(Appointment appointment) {
        Appointment existing = read(appointment.getAppointmentId());
        if (existing != null) {
            this.appointmentSet.remove(existing);
            this.appointmentSet.add(appointment);
            return appointment;
        }
        return null;
    }

    @Override
    public boolean delete(Integer appointmentId) {
        Appointment existing = read(appointmentId);
        if (existing != null) {
            this.appointmentSet.remove(existing);
            return true;
        }
        return false;
    }

    @Override
    public List<Appointment> getAll() {
        return new ArrayList<>(this.appointmentSet);
    }
}
