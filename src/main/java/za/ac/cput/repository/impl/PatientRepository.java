package za.ac.cput.repository.impl;

import za.ac.cput.domain.Patient;
import za.ac.cput.repository.IPatientRepository;

import java.util.ArrayList;
import java.util.List;

public class PatientRepository implements IPatientRepository {

    private static PatientRepository repository = null;
    private List<Patient>patientList;

    private PatientRepository(){
        patientList = new ArrayList<>();
    }

    public static IPatientRepository getRepository(){
        if(repository == null){

            repository = new PatientRepository();
        }
        return repository;
    }

    @Override
    public Patient create(Patient patient) {
        patientList.add(patient);
        return patient;
    }

    @Override
    public Patient read(int patientId) {
        for(Patient patient: patientList){
            if(patient.getPatientId() == patientId){
                return patient;
            }
        }
        return null;
    }

    @Override
    public Patient update(Patient patient) {
        Patient oldPatient = read(patient.getPatientId());

        if(oldPatient !=null){
            patientList.remove(oldPatient);
            patientList.add(patient);
            return patient;
        }
        return null;
    }

    @Override
    public boolean delete(int patientId) {
        Patient patient = read(patientId);

        if(patient !=null){
            patientList.remove(patient);
            return true;
        }
        return false;
    }

    @Override
    public List<Patient> getAll() {
        return patientList;
    }
}

