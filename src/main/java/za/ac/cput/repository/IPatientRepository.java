package za.ac.cput.repository;

import za.ac.cput.domain.Patient;

import java.util.List;

public interface IPatientRepository {

    Patient create(Patient patient);
    Patient read(int patientId);
    Patient update(Patient patient);
    boolean delete(int patientId);

    List<Patient>getAll();
}
