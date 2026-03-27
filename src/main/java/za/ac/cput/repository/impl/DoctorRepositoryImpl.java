package za.ac.cput.repository.impl;

import za.ac.cput.domain.Doctor;
import za.ac.cput.repository.IDoctorRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorRepositoryImpl implements IDoctorRepository {

    private static DoctorRepositoryImpl instance;
    private final Map<Integer, Doctor> store = new HashMap<>();

    private DoctorRepositoryImpl() {}

    public static DoctorRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new DoctorRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Doctor create(Doctor doctor) {
        if (doctor == null) return null;
        if (store.containsKey(doctor.getDoctorId())) return null;
        store.put(doctor.getDoctorId(), doctor);
        return doctor;
    }

    @Override
    public Doctor read(Integer id) {
        if (id == null || id <= 0) return null;
        return store.get(id);
    }

    @Override
    public Doctor update(Doctor doctor) {
        if (doctor == null) return null;
        if (!store.containsKey(doctor.getDoctorId())) return null;
        store.put(doctor.getDoctorId(), doctor);
        return doctor;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null || id <= 0) return false;
        if (!store.containsKey(id)) return false;
        store.remove(id);
        return true;
    }

    @Override
    public List<Doctor> getAll() {
        return new ArrayList<>(store.values());
    }
}