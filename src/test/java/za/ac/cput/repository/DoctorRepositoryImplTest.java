package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Doctor;
import za.ac.cput.factory.DoctorFactory;
import za.ac.cput.repository.impl.DoctorRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoctorRepositoryImplTest {

    private DoctorRepositoryImpl repository;
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        repository = DoctorRepositoryImpl.getInstance();

        doctor = DoctorFactory.buildDoctor(
                1,
                "James",
                "Smith",
                "Cardiologist",
                "0821234567",
                "james.smith@clinic.com"
        );
    }

    // Create tests
    @Test
    void testCreateSuccess() {
        Doctor created = repository.create(doctor);
        assertNotNull(created);
        assertEquals(doctor.getDoctorId(), created.getDoctorId());
    }

    @Test
    void testCreateNullDoctor() {
        Doctor created = repository.create(null);
        assertNull(created);
    }

    @Test
    void testCreateDuplicateDoctor() {
        repository.create(doctor);
        Doctor duplicate = repository.create(doctor);
        assertNull(duplicate);
    }

    // Read tests
    @Test
    void testReadSuccess() {
        repository.create(doctor);
        Doctor found = repository.read(1);
        assertNotNull(found);
        assertEquals(1, found.getDoctorId());
    }

    @Test
    void testReadNotFound() {
        Doctor found = repository.read(999);
        assertNull(found);
    }

    @Test
    void testReadInvalidId() {
        Doctor found = repository.read(0);
        assertNull(found);
    }

    // Update tests
    @Test
    void testUpdateSuccess() {
        repository.create(doctor);
        Doctor updated = new Doctor.Builder()
                .copy(doctor)
                .setDoctorSpecialty("Neurologist")
                .build();
        Doctor result = repository.update(updated);
        assertNotNull(result);
        assertEquals("Neurologist", result.getDoctorSpecialty());
    }

    @Test
    void testUpdateNonExistentDoctor() {
        Doctor nonExistent = DoctorFactory.buildDoctor(
                99,
                "John",
                "Doe",
                "Dermatologist",
                "0831234567",
                "john.doe@clinic.com"
        );
        Doctor result = repository.update(nonExistent);
        assertNull(result);
    }

    @Test
    void testUpdateNullDoctor() {
        Doctor result = repository.update(null);
        assertNull(result);
    }

    // Delete tests
    @Test
    void testDeleteSuccess() {
        repository.create(doctor);
        boolean deleted = repository.delete(1);
        assertTrue(deleted);
        assertNull(repository.read(1));
    }

    @Test
    void testDeleteNonExistent() {
        boolean deleted = repository.delete(999);
        assertFalse(deleted);
    }

    @Test
    void testDeleteInvalidId() {
        boolean deleted = repository.delete(0);
        assertFalse(deleted);
    }

    // GetAll tests
    @Test
    void testGetAllNotEmpty() {
        repository.create(doctor);
        List<Doctor> doctors = repository.getAll();
        assertNotNull(doctors);
        assertFalse(doctors.isEmpty());
    }

    @Test
    void testGetAllContainsCreatedDoctor() {
        repository.create(doctor);
        List<Doctor> doctors = repository.getAll();
        assertTrue(doctors.stream()
                .anyMatch(d -> d.getDoctorId() == doctor.getDoctorId()));
    }
}
