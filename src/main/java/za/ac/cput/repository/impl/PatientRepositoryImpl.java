// Aidan Barends 230255639
// Date Completed 25 March
package za.ac.cput.repository.impl;

import za.ac.cput.domain.Patient;
import za.ac.cput.repository.IPatientRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientRepositoryImpl implements IPatientRepository {

    private static PatientRepositoryImpl instance;
    private final Map<Integer, Patient> store = new HashMap<>();

    private PatientRepositoryImpl() {}

    public static PatientRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new PatientRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Patient create(Patient patient) {
        if (patient == null) return null;
        if (store.containsKey(patient.getPatientId())) return null;
        store.put(patient.getPatientId(), patient);
        return patient;
    }

    @Override
    public Patient read(Integer id) {
        if (id == null || id <= 0) return null;
        return store.get(id);
    }

    @Override
    public Patient update(Patient patient) {
        if (patient == null) return null;
        if (!store.containsKey(patient.getPatientId())) return null;
        store.put(patient.getPatientId(), patient);
        return patient;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null || id <= 0) return false;
        if (!store.containsKey(id)) return false;
        store.remove(id);
        return true;
    }

    @Override
    public List<Patient> getAll() {
        return new ArrayList<>(store.values());
    }
}