package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Doctor;

import static org.junit.jupiter.api.Assertions.*;

class DoctorFactoryTest {

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = DoctorFactory.buildDoctor(
                1,
                "James",
                "Smith",
                "Cardiologist",
                "0821234567",
                "james.smith@clinic.com"
        );
    }

    // Success tests
    @Test
    void testDoctorIsNotNull() {
        assertNotNull(doctor);
    }

    @Test
    void testDoctorId() {
        assertEquals(1, doctor.getDoctorId());
    }

    @Test
    void testDoctorName() {
        assertEquals("James", doctor.getDoctorName());
    }

    @Test
    void testDoctorSurname() {
        assertEquals("Smith", doctor.getDoctorSurname());
    }

    @Test
    void testDoctorSpecialty() {
        assertEquals("Cardiologist", doctor.getDoctorSpecialty());
    }

    @Test
    void testDoctorCell() {
        assertEquals("0821234567", doctor.getDoctorCell());
    }

    @Test
    void testDoctorEmail() {
        assertEquals("james.smith@clinic.com", doctor.getDoctorEmail());
    }

    @Test
    void testGetFullName() {
        assertEquals("Dr. James Smith", doctor.getFullName());
    }

    @Test
    void testUpdateContactDetails() {
        doctor.updateContactDetails("0839876543", "james.new@clinic.com");
        assertEquals("0839876543", doctor.getDoctorCell());
        assertEquals("james.new@clinic.com", doctor.getDoctorEmail());
    }

    @Test
    void testCopyBuilder() {
        Doctor copiedDoctor = new Doctor.Builder()
                .copy(doctor)
                .build();
        assertNotNull(copiedDoctor);
        assertEquals(doctor.getDoctorId(), copiedDoctor.getDoctorId());
        assertEquals(doctor.getDoctorName(), copiedDoctor.getDoctorName());
        assertEquals(doctor.getDoctorSurname(), copiedDoctor.getDoctorSurname());
        assertEquals(doctor.getDoctorSpecialty(), copiedDoctor.getDoctorSpecialty());
        assertEquals(doctor.getDoctorCell(), copiedDoctor.getDoctorCell());
        assertEquals(doctor.getDoctorEmail(), copiedDoctor.getDoctorEmail());
    }

    @Test
    void testToString() {
        String result = doctor.toString();
        assertNotNull(result);
        assertTrue(result.contains("James"));
        assertTrue(result.contains("Smith"));
        assertTrue(result.contains("Cardiologist"));
    }

    // Validation failure tests
    @Test
    void testInvalidId() {
        Doctor invalid = DoctorFactory.buildDoctor(
                0, "James", "Smith", "Cardiologist", "0821234567", "james.smith@clinic.com");
        assertNull(invalid);
    }

    @Test
    void testInvalidName() {
        Doctor invalid = DoctorFactory.buildDoctor(
                1, "", "Smith", "Cardiologist", "0821234567", "james.smith@clinic.com");
        assertNull(invalid);
    }

    @Test
    void testInvalidSurname() {
        Doctor invalid = DoctorFactory.buildDoctor(
                1, "James", "", "Cardiologist", "0821234567", "james.smith@clinic.com");
        assertNull(invalid);
    }

    @Test
    void testInvalidSpecialty() {
        Doctor invalid = DoctorFactory.buildDoctor(
                1, "James", "Smith", "", "0821234567", "james.smith@clinic.com");
        assertNull(invalid);
    }

    @Test
    void testInvalidEmail() {
        Doctor invalid = DoctorFactory.buildDoctor(
                1, "James", "Smith", "Cardiologist", "0821234567", "invalidemail");
        assertNull(invalid);
    }

    @Test
    void testInvalidCell() {
        Doctor invalid = DoctorFactory.buildDoctor(
                1, "James", "Smith", "Cardiologist", "082", "james.smith@clinic.com");
        assertNull(invalid);
    }
}