// Matthew Barron 230398863
package za.ac.cput.factory;

import za.ac.cput.domain.ClinicStaff;
import za.ac.cput.util.Helper;

public class ClinicStaffFactory {

    public static ClinicStaff createClinicStaff(
            int id,
            String name,
            String surname,
            String role,
            String email,
            String cell) {

        if (!Helper.isValidId(id)) return null;
        if (Helper.isNullOrEmpty(name)) return null;
        if (Helper.isNullOrEmpty(surname)) return null;
        if (Helper.isNullOrEmpty(role)) return null;
        if (!Helper.isValidEmail(email)) return null;
        if (Helper.isNullOrEmpty(cell) || cell.length() < 10
                || !cell.matches("\\d+")) return null;

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