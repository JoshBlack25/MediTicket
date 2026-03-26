// Matthew Barron 230398863
package za.ac.cput.repository;

import za.ac.cput.domain.ClinicStaff;

public interface ClinicStaffRepository {
    ClinicStaff create(ClinicStaff staff);
    ClinicStaff read(Integer id);
    ClinicStaff update(ClinicStaff staff);
    boolean delete(Integer id);
}