package za.ac.cput.factory;

import za.ac.cput.domain.Doctor;
import za.ac.cput.util.Helper;

public class DoctorFactory {

    public static Doctor buildDoctor(
            int doctorId,
            String doctorName,
            String doctorSurname,
            String doctorSpecialty,
            String doctorCell,
            String doctorEmail) {

        if (!Helper.isValidId(doctorId)) return null;
        if (Helper.isNullOrEmpty(doctorName)) return null;
        if (Helper.isNullOrEmpty(doctorSurname)) return null;
        if (Helper.isNullOrEmpty(doctorSpecialty)) return null;
        if (!Helper.isValidEmail(doctorEmail)) return null;
        if (Helper.isNullOrEmpty(doctorCell) || doctorCell.length() < 10
                || !doctorCell.matches("\\d+")) return null;

        return new Doctor.Builder()
                .setDoctorId(doctorId)
                .setDoctorName(doctorName)
                .setDoctorSurname(doctorSurname)
                .setDoctorSpecialty(doctorSpecialty)
                .setDoctorCell(doctorCell)
                .setDoctorEmail(doctorEmail)
                .build();
    }
}