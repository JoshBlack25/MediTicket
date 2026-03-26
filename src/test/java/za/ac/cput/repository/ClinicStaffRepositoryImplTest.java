// Matthew Barron 230398863
package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.ClinicStaff;
import za.ac.cput.factory.ClinicStaffFactory;
import za.ac.cput.repository.impl.ClinicStaffRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;

class ClinicStaffRepositoryImplTest {

    private ClinicStaffRepositoryImpl repo;
    private ClinicStaff staff;

    @BeforeEach
    void setUp() {
        repo = new ClinicStaffRepositoryImpl();
        staff = ClinicStaffFactory.createClinicStaff(
                1,
                "John",
                "Doe",
                "Nurse",
                "john.doe@example.com",
                "0821234567"
        );
        repo.create(staff);
    }

    @Test
    void create() {
        ClinicStaff created = repo.create(staff);
        assertNotNull(created);
        assertEquals(1, created.getStaffId());
    }

    @Test
    void read() {
        ClinicStaff readStaff = repo.read(1);
        assertNotNull(readStaff);
        assertEquals("John", readStaff.getStaffName());
    }

    @Test
    void update() {
        ClinicStaff updated = new ClinicStaff.Builder()
                .setStaffId(1)
                .setStaffName("Jane")
                .setStaffSurname("Doe")
                .setStaffRole("Doctor")
                .setStaffEmail("jane.doe@example.com")
                .setStaffCell("0827654321")
                .build();

        ClinicStaff result = repo.update(updated);
        assertNotNull(result);
        assertEquals("Jane", result.getStaffName());
        assertEquals("Doctor", result.getStaffRole());
    }

    @Test
    void delete() {
        boolean deleted = repo.delete(1);
        assertTrue(deleted);
        assertNull(repo.read(1));
    }

    @Test
    void getAll() {
        var allStaff = repo.getAll();
        assertFalse(allStaff.isEmpty());
        assertTrue(allStaff.contains(staff));
    }
}
