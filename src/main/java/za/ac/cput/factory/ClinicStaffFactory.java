package za.ac.cput.factory;

import za.ac.cput.domain.ClinicStaff;

public class ClinicStaffFactory {

    public static ClinicStaff createClinicStaff(
            int id,
            String name,
            String surname,
            String role,
            String email,
            String cell) {

        if (name == null || name.isEmpty()) return null;
        if (surname == null || surname.isEmpty()) return null;
        if (role == null || role.isEmpty()) return null;
        if (email == null || !email.contains("@")) return null;
        if (cell == null || cell.length() < 10) return null;

        return new ClinicStaff.Builder()
                .setStaffId(id)
                .setStaffName(name)
                .setStaffSurname(surname)
                .setStaffRole(role)
                .setStaffEmail(email)
                .setStaffCell(cell)
                .build();
    }
}