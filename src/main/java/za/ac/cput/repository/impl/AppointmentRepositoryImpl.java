/* AppointmentRepositoryImpl.java
   Author: Joshua Peter Bonzet (221312536)
   Date: 26 March 2026
*/
package za.ac.cput.repository.impl;

import za.ac.cput.domain.Appointment;
import za.ac.cput.repository.IAppointmentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentRepositoryImpl implements IAppointmentRepository {

    private static AppointmentRepositoryImpl instance;
    private final Map<Integer, Appointment> store = new HashMap<>();

    private AppointmentRepositoryImpl() {}

    public static AppointmentRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new AppointmentRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Appointment create(Appointment appointment) {
        if (appointment == null) return null;
        if (store.containsKey(appointment.getAppointmentId())) return null;
        store.put(appointment.getAppointmentId(), appointment);
        return appointment;
    }

    @Override
    public Appointment read(Integer id) {
        if (id == null || id <= 0) return null;
        return store.get(id);
    }

    @Override
    public Appointment update(Appointment appointment) {
        if (appointment == null) return null;
        if (!store.containsKey(appointment.getAppointmentId())) return null;
        store.put(appointment.getAppointmentId(), appointment);
        return appointment;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null || id <= 0) return false;
        if (!store.containsKey(id)) return false;
        store.remove(id);
        return true;
    }

    @Override
    public List<Appointment> getAll() {
        return new ArrayList<>(store.values());
    }
}