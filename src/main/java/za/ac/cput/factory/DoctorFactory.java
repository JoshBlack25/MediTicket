package za.ac.cput.factory;

import za.ac.cput.domain.Doctor;

public class DoctorFactory {

    public static Doctor buildDoctor(
            int doctorId,
            String doctorName,
            String doctorSurname,
            String doctorSpecialty,
            String doctorCell,
            String doctorEmail) {

        if (doctorId <= 0) return null;
        if (doctorName == null || doctorName.isEmpty()) return null;
        if (doctorSurname == null || doctorSurname.isEmpty()) return null;
        if (doctorSpecialty == null || doctorSpecialty.isEmpty()) return null;
        if (doctorEmail == null || !doctorEmail.contains("@") || !doctorEmail.contains(".")) return null;
        if (doctorCell == null || doctorCell.length() < 10 || !doctorCell.matches("\\d+")) return null;

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
