// Matthew Barron 230398863
package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.ClinicStaff;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClinicStaffFactoryTest {

    private static ClinicStaff staff;

    @BeforeAll
    static void setUp() {
        staff = ClinicStaffFactory.createClinicStaff(
                1,
                "John",
                "Doe",
                "Nurse",
                "john.doe@example.com",
                "0821234567"
        );
    }

    @Test
    @Order(1)
    void testCreateClinicStaff_Success() {
        assertNotNull(staff);
    }

    @Test
    @Order(2)
    void testStaffId() {
        assertEquals(1, staff.getStaffId());
    }

    @Test
    @Order(3)
    void testStaffName() {
        assertEquals("John", staff.getStaffName());
    }

    @Test
    @Order(4)
    void testStaffSurname() {
        assertEquals("Doe", staff.getStaffSurname());
    }

    @Test
    @Order(5)
    void testStaffRole() {
        assertEquals("Nurse", staff.getStaffRole());
    }

    @Test
    @Order(6)
    void testStaffEmail() {
        assertEquals("john.doe@example.com", staff.getStaffEmail());
    }

    @Test
    @Order(7)
    void testStaffCell() {
        assertEquals("0821234567", staff.getStaffCell());
    }

    @Test
    @Order(8)
    void testGetFullName() {
        assertEquals("John Doe", staff.getFullName());
    }

    @Test
    @Order(9)
    void testCopyBuilder() {
        ClinicStaff copy = new ClinicStaff.Builder()
                .copy(staff)
                .build();
        assertNotNull(copy);
        assertEquals(staff.getStaffId(), copy.getStaffId());
        assertEquals(staff.getStaffName(), copy.getStaffName());
        assertEquals(staff.getStaffSurname(), copy.getStaffSurname());
        assertEquals(staff.getStaffRole(), copy.getStaffRole());
        assertEquals(staff.getStaffEmail(), copy.getStaffEmail());
        assertEquals(staff.getStaffCell(), copy.getStaffCell());
    }

    @Test
    @Order(10)
    void testToString() {
        String result = staff.toString();
        assertNotNull(result);
        assertTrue(result.contains("John"));
        assertTrue(result.contains("Doe"));
        assertTrue(result.contains("Nurse"));
    }

    @Test
    @Order(11)
    void testInvalidId_Fails() {
        ClinicStaff invalid = ClinicStaffFactory.createClinicStaff(
                0, "John", "Doe", "Nurse",
                "john.doe@example.com", "0821234567");
        assertNull(invalid);
    }

    @Test
    @Order(12)
    void testInvalidName_Fails() {
        ClinicStaff invalid = ClinicStaffFactory.createClinicStaff(
                1, "", "Doe", "Nurse",
                "john.doe@example.com", "0821234567");
        assertNull(invalid);
    }

    @Test
    @Order(13)
    void testInvalidSurname_Fails() {
        ClinicStaff invalid = ClinicStaffFactory.createClinicStaff(
                1, "John", "", "Nurse",
                "john.doe@example.com", "0821234567");
        assertNull(invalid);
    }

    @Test
    @Order(14)
    void testInvalidRole_Fails() {
        ClinicStaff invalid = ClinicStaffFactory.createClinicStaff(
                1, "John", "Doe", "",
                "john.doe@example.com", "0821234567");
        assertNull(invalid);
    }

    @Test
    @Order(15)
    void testInvalidEmail_Fails() {
        ClinicStaff invalid = ClinicStaffFactory.createClinicStaff(
                1, "John", "Doe", "Nurse",
                "bademail", "0821234567");
        assertNull(invalid);
    }

    @Test
    @Order(16)
    void testInvalidCell_Fails() {
        ClinicStaff invalid = ClinicStaffFactory.createClinicStaff(
                1, "John", "Doe", "Nurse",
                "john.doe@example.com", "123");
        assertNull(invalid);
    }

    @Test
    @Order(17)
    void testNonNumericCell_Fails() {
        ClinicStaff invalid = ClinicStaffFactory.createClinicStaff(
                1, "John", "Doe", "Nurse",
                "john.doe@example.com", "abcdefghij");
        assertNull(invalid);
    }

    @Test
    @Order(18)
    void testAllNull_Fails() {
        ClinicStaff invalid = ClinicStaffFactory.createClinicStaff(
                0, null, null, null, null, null);
        assertNull(invalid);
    }
}