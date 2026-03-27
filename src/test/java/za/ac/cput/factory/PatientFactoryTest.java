// Aidan Barends 230255639
// Date Completed 24 March
package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.Patient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PatientFactoryTest {

    private static Patient patient;

    @BeforeAll
    static void setUp() {
        patient = PatientFactory.createPatient(
                21,
                "Aidan",
                "Barends",
                "0712345678",
                "aidanbarends@cput.ac.za",
                LocalDate.of(2004, 11, 10)
        );
    }

    @Test
    @Order(1)
    void testCreatePatient_Success() {
        assertNotNull(patient);
    }

    @Test
    @Order(2)
    void testPatientId() {
        assertEquals(21, patient.getPatientId());
    }

    @Test
    @Order(3)
    void testPatientName() {
        assertEquals("Aidan", patient.getPatientName());
    }

    @Test
    @Order(4)
    void testPatientSurname() {
        assertEquals("Barends", patient.getPatientSurname());
    }

    @Test
    @Order(5)
    void testPatientCell() {
        assertEquals("0712345678", patient.getPatientCell());
    }

    @Test
    @Order(6)
    void testPatientEmail() {
        assertEquals("aidanbarends@cput.ac.za", patient.getPatientEmail());
    }

    @Test
    @Order(7)
    void testPatientDOB() {
        assertEquals(LocalDate.of(2004, 11, 10), patient.getPatientDOB());
    }

    @Test
    @Order(8)
    void testCopyBuilder() {
        Patient copy = new Patient.Builder()
                .copy(patient)
                .build();
        assertNotNull(copy);
        assertEquals(patient.getPatientId(), copy.getPatientId());
        assertEquals(patient.getPatientName(), copy.getPatientName());
        assertEquals(patient.getPatientSurname(), copy.getPatientSurname());
        assertEquals(patient.getPatientCell(), copy.getPatientCell());
        assertEquals(patient.getPatientEmail(), copy.getPatientEmail());
        assertEquals(patient.getPatientDOB(), copy.getPatientDOB());
    }

    @Test
    @Order(9)
    void testToString() {
        String result = patient.toString();
        assertNotNull(result);
        assertTrue(result.contains("Aidan"));
        assertTrue(result.contains("Barends"));
    }

    @Test
    @Order(10)
    void testInvalidId_Fails() {
        Patient invalid = PatientFactory.createPatient(
                0, "Aidan", "Barends", "0712345678",
                "aidanbarends@cput.ac.za", LocalDate.of(2004, 11, 10));
        assertNull(invalid);
    }

    @Test
    @Order(11)
    void testInvalidName_Fails() {
        Patient invalid = PatientFactory.createPatient(
                1, "", "Barends", "0712345678",
                "aidanbarends@cput.ac.za", LocalDate.of(2004, 11, 10));
        assertNull(invalid);
    }

    @Test
    @Order(12)
    void testInvalidSurname_Fails() {
        Patient invalid = PatientFactory.createPatient(
                1, "Aidan", "", "0712345678",
                "aidanbarends@cput.ac.za", LocalDate.of(2004, 11, 10));
        assertNull(invalid);
    }

    @Test
    @Order(13)
    void testInvalidEmail_Fails() {
        Patient invalid = PatientFactory.createPatient(
                1, "Aidan", "Barends", "0712345678",
                "invalidemail", LocalDate.of(2004, 11, 10));
        assertNull(invalid);
    }

    @Test
    @Order(14)
    void testFutureDOB_Fails() {
        Patient invalid = PatientFactory.createPatient(
                1, "Aidan", "Barends", "0712345678",
                "aidanbarends@cput.ac.za", LocalDate.of(2030, 1, 1));
        assertNull(invalid);
    }

    @Test
    @Order(15)
    void testNullDOB_Fails() {
        Patient invalid = PatientFactory.createPatient(
                1, "Aidan", "Barends", "0712345678",
                "aidanbarends@cput.ac.za", null);
        assertNull(invalid);
    }
}