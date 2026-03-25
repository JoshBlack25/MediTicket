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