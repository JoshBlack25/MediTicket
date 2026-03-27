package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.Doctor;

import static org.junit.jupiter.api.Assertions.*;
// Jaden Clayton Abrahams: 222206721
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DoctorFactoryTest {

    private static Doctor doctor;

    @BeforeAll
    static void setUp() {
        doctor = DoctorFactory.buildDoctor(
                1,
                "James",
                "Smith",
                "Cardiologist",
                "0821234567",
                "james.smith@clinic.com"
        );
    }

    @Test
    @Order(1)
    void testDoctorIsNotNull() {
        assertNotNull(doctor);
    }

    @Test
    @Order(2)
    void testDoctorId() {
        assertEquals(1, doctor.getDoctorId());
    }

    @Test
    @Order(3)
    void testDoctorName() {
        assertEquals("James", doctor.getDoctorName());
    }

    @Test
    @Order(4)
    void testDoctorSurname() {
        assertEquals("Smith", doctor.getDoctorSurname());
    }

    @Test
    @Order(5)
    void testDoctorSpecialty() {
        assertEquals("Cardiologist", doctor.getDoctorSpecialty());
    }

    @Test
    @Order(6)
    void testDoctorCell() {
        assertEquals("0821234567", doctor.getDoctorCell());
    }

    @Test
    @Order(7)
    void testDoctorEmail() {
        assertEquals("james.smith@clinic.com", doctor.getDoctorEmail());
    }

    @Test
    @Order(8)
    void testGetFullName() {
        assertEquals("Dr. James Smith", doctor.getFullName());
    }

    @Test
    @Order(9)
    void testCopyBuilder() {
        Doctor copy = new Doctor.Builder()
                .copy(doctor)
                .build();
        assertNotNull(copy);
        assertEquals(doctor.getDoctorId(), copy.getDoctorId());
        assertEquals(doctor.getDoctorName(), copy.getDoctorName());
        assertEquals(doctor.getDoctorSurname(), copy.getDoctorSurname());
        assertEquals(doctor.getDoctorSpecialty(), copy.getDoctorSpecialty());
        assertEquals(doctor.getDoctorCell(), copy.getDoctorCell());
        assertEquals(doctor.getDoctorEmail(), copy.getDoctorEmail());
    }

    @Test
    @Order(10)
    void testToString() {
        String result = doctor.toString();
        assertNotNull(result);
        assertTrue(result.contains("James"));
        assertTrue(result.contains("Smith"));
        assertTrue(result.contains("Cardiologist"));
    }

    @Test
    @Order(11)
    void testInvalidId_Fails() {
        Doctor invalid = DoctorFactory.buildDoctor(
                0, "James", "Smith", "Cardiologist",
                "0821234567", "james.smith@clinic.com");
        assertNull(invalid);
    }

    @Test
    @Order(12)
    void testInvalidName_Fails() {
        Doctor invalid = DoctorFactory.buildDoctor(
                1, "", "Smith", "Cardiologist",
                "0821234567", "james.smith@clinic.com");
        assertNull(invalid);
    }

    @Test
    @Order(13)
    void testInvalidSurname_Fails() {
        Doctor invalid = DoctorFactory.buildDoctor(
                1, "James", "", "Cardiologist",
                "0821234567", "james.smith@clinic.com");
        assertNull(invalid);
    }

    @Test
    @Order(14)
    void testInvalidSpecialty_Fails() {
        Doctor invalid = DoctorFactory.buildDoctor(
                1, "James", "Smith", "",
                "0821234567", "james.smith@clinic.com");
        assertNull(invalid);
    }

    @Test
    @Order(15)
    void testInvalidEmail_Fails() {
        Doctor invalid = DoctorFactory.buildDoctor(
                1, "James", "Smith", "Cardiologist",
                "0821234567", "invalidemail");
        assertNull(invalid);
    }

    @Test
    @Order(16)
    void testInvalidCell_Fails() {
        Doctor invalid = DoctorFactory.buildDoctor(
                1, "James", "Smith", "Cardiologist",
                "082", "james.smith@clinic.com");
        assertNull(invalid);
    }
}