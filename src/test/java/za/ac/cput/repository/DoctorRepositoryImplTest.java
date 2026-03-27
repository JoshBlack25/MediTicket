package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.Doctor;
import za.ac.cput.factory.DoctorFactory;
import za.ac.cput.repository.impl.DoctorRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Jaden Clayton Abrahams: 222206721

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DoctorRepositoryImplTest {

    private static DoctorRepositoryImpl repository;
    private static Doctor doctor;

    @BeforeAll
    static void setUp() throws Exception {
        java.lang.reflect.Field field =
                DoctorRepositoryImpl.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, null);

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

    @Test
    @Order(1)
    void testSingleton_SameInstance() {
        DoctorRepositoryImpl instance1 = DoctorRepositoryImpl.getInstance();
        DoctorRepositoryImpl instance2 = DoctorRepositoryImpl.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @Order(2)
    void testCreate_Success() {
        Doctor created = repository.create(doctor);
        assertNotNull(created);
        assertEquals(doctor.getDoctorId(), created.getDoctorId());
        System.out.println(created);
    }

    @Test
    @Order(3)
    void testCreate_Null_Fails() {
        Doctor created = repository.create(null);
        assertNull(created);
    }

    @Test
    @Order(4)
    void testCreate_Duplicate_Fails() {
        Doctor duplicate = repository.create(doctor);
        assertNull(duplicate);
    }

    @Test
    @Order(5)
    void testRead_Success() {
        Doctor found = repository.read(1);
        assertNotNull(found);
        assertEquals(1, found.getDoctorId());
        System.out.println(found);
    }

    @Test
    @Order(6)
    void testRead_NonExistent_Fails() {
        Doctor found = repository.read(999);
        assertNull(found);
    }

    @Test
    @Order(7)
    void testRead_InvalidId_Fails() {
        Doctor found = repository.read(0);
        assertNull(found);
    }

    @Test
    @Order(8)
    void testUpdate_Success() {
        Doctor updated = new Doctor.Builder()
                .copy(doctor)
                .setDoctorSpecialty("Neurologist")
                .build();
        Doctor result = repository.update(updated);
        assertNotNull(result);
        assertEquals("Neurologist", result.getDoctorSpecialty());
        System.out.println(result);
    }

    @Test
    @Order(9)
    void testUpdate_NonExistent_Fails() {
        Doctor nonExistent = DoctorFactory.buildDoctor(
                99, "John", "Doe", "Dermatologist",
                "0831234567", "john.doe@clinic.com");
        Doctor result = repository.update(nonExistent);
        assertNull(result);
    }

    @Test
    @Order(10)
    void testUpdate_Null_Fails() {
        Doctor result = repository.update(null);
        assertNull(result);
    }

    @Test
    @Order(11)
    void testGetAll_NotEmpty() {
        List<Doctor> doctors = repository.getAll();
        assertNotNull(doctors);
        assertFalse(doctors.isEmpty());
        System.out.println(doctors);
    }

    @Test
    @Order(12)
    void testGetAll_ContainsCreatedDoctor() {
        List<Doctor> doctors = repository.getAll();
        assertTrue(doctors.stream()
                .anyMatch(d -> d.getDoctorId() == doctor.getDoctorId()));
    }

    @Test
    @Order(13)
    void testGetAll_IsDefensiveCopy() {
        List<Doctor> listA = repository.getAll();
        List<Doctor> listB = repository.getAll();
        assertNotSame(listA, listB);
    }

    @Test
    @Order(14)
    void testDelete_Success() {
        boolean deleted = repository.delete(1);
        assertTrue(deleted);
        assertNull(repository.read(1));
    }

    @Test
    @Order(15)
    void testDelete_NonExistent_Fails() {
        boolean deleted = repository.delete(999);
        assertFalse(deleted);
    }

    @Test
    @Order(16)
    void testDelete_InvalidId_Fails() {
        boolean deleted = repository.delete(0);
        assertFalse(deleted);
    }
}