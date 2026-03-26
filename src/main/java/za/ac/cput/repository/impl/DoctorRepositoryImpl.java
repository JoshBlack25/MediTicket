package za.ac.cput.repository.impl;

import za.ac.cput.domain.Doctor;
import za.ac.cput.repository.IDoctorRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorRepositoryImpl implements IDoctorRepository {

    private static DoctorRepositoryImpl instance;
    private final Map<Integer, Doctor> doctorStore = new HashMap<>();

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
        if (doctorStore.containsKey(doctor.getDoctorId())) return null;
        doctorStore.put(doctor.getDoctorId(), doctor);
        return doctor;
    }

    @Override
    public Doctor read(Integer id) {
        if (id == null || id <= 0) return null;
        return doctorStore.get(id);
    }

    @Override
    public Doctor update(Doctor doctor) {
        if (doctor == null) return null;
        if (!doctorStore.containsKey(doctor.getDoctorId())) return null;
        doctorStore.put(doctor.getDoctorId(), doctor);
        return doctor;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null || id <= 0) return false;
        if (!doctorStore.containsKey(id)) return false;
        doctorStore.remove(id);
        return true;
    }

    @Override
    public List<Doctor> getAll() {
        return new ArrayList<>(doctorStore.values());
    }
}
