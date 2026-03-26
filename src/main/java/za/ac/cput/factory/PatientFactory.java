package za.ac.cput.factory;

import za.ac.cput.util.Helper;
import za.ac.cput.domain.Patient;

import java.time.LocalDate;

public class PatientFactory {

    public static Patient createPatient(int patientId, String patientName, String patientSurname,String patientCell, String patientEmail, LocalDate patientDOB){
        if(!Helper.isValidId(patientId) || Helper.isNullOrEmpty(patientName) || Helper.isNullOrEmpty(patientSurname) || Helper.isNullOrEmpty(patientCell) || !Helper.isValidEmail(patientEmail) || !Helper.isValidDate(patientDOB)){
            return null;
        }
        return new Patient.Builder()
                .setPatientId(patientId)
                .setPatientName(patientName)
                .setPatientSurname(patientSurname)
                .setPatientCell(patientCell)
                .setPatientEmail(patientEmail)
                .setPatientDOB(patientDOB)
                .build();
    }
}