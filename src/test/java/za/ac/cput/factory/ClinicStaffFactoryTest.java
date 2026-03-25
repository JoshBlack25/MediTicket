package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.ClinicStaff;

import static org.junit.jupiter.api.Assertions.*;

class ClinicStaffFactoryTest {

    @Test
    void createClinicStaff() {
        ClinicStaff staff = ClinicStaffFactory.createClinicStaff(
                1,
                "John",
                "Doe",
                "Nurse",
                "john.doe@example.com",
                "0821234567"
        );

        assertNotNull(staff);
    }

    @Test
    void createClinicStaffFail() {
        ClinicStaff staff = ClinicStaffFactory.createClinicStaff(
                1,
                "",
                "Doe",
                "Nurse",
                "bademail",
                "123"
        );

        assertNull(staff);
    }
}