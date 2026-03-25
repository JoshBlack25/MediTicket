/* DoctorFactoryTest.java
   TDD test class for DoctorFactory
   Author: Jaden A (222206721)
   Date: 25 March 2026
*/
package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Doctor;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorFactoryTest {

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = DoctorFactory.createDoctor(1001, "John", "Doe", "Cardiology", "0123456789", "john.doe@example.com");
    }

    @Test
    void testCreateDoctor() {
        assertNotNull(doctor);
    }

    @Test
    void testDoctorId() {
        assertEquals(1001, doctor.getDoctorId());
    }

    @Test
    void testDoctorName() {
        assertEquals("John", doctor.getDoctorName());
    }

    @Test
    void testDoctorSurname() {
        assertEquals("Doe", doctor.getDoctorSurname());
    }

    @Test
    void testDoctorSpecialty() {
        assertEquals("Cardiology", doctor.getDoctorSpecialty());
    }

    @Test
    void testDoctorCell() {
        assertEquals("0123456789", doctor.getDoctorCell());
    }

    @Test
    void testDoctorEmail() {
        assertEquals("john.doe@example.com", doctor.getDoctorEmail());
    }

    @Test
    void testToStringContainsAllAttributes() {
        String toString = doctor.toString();
        assertTrue(toString.contains("1001"));
        assertTrue(toString.contains("John"));
        assertTrue(toString.contains("Doe"));
        assertTrue(toString.contains("Cardiology"));
        assertTrue(toString.contains("0123456789"));
        assertTrue(toString.contains("john.doe@example.com"));
    }
}
