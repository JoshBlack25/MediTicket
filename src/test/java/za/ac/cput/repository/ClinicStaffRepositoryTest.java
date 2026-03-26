// Matthew Barron 230398863
package za.ac.cput.repository;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.ClinicStaff;
import za.ac.cput.factory.ClinicStaffFactory;
import za.ac.cput.repository.impl.ClinicStaffRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;

class ClinicStaffRepositoryTest {

    ClinicStaffRepositoryImpl repo = new ClinicStaffRepositoryImpl();

    ClinicStaff staff = ClinicStaffFactory.createClinicStaff(
            1,
            "John",
            "Doe",
            "Nurse",
            "john.doe@example.com",
            "0821234567"
    );

    @Test
    void create() {
        assertNotNull(repo.create(staff));
    }

    @Test
    void read() {
        repo.create(staff);
        assertNotNull(repo.read(1));
    }

    @Test
    void update() {
        repo.create(staff);

        ClinicStaff updated = new ClinicStaff.Builder()
                .setStaffId(1)
                .setStaffName("Jane")
                .setStaffSurname("Doe")
                .setStaffRole("Doctor")
                .setStaffEmail("jane.doe@example.com")
                .setStaffCell("0827654321")
                .build();

        repo.update(updated);

        assertEquals("Jane", repo.read(1).getStaffName());
    }

    @Test
    void delete() {
        repo.create(staff);
        repo.delete(1);
        assertNull(repo.read(1));
    }
}