// Aidan Barends 230255639
// Date Completed: 26 March
package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.Patient;
import za.ac.cput.factory.PatientFactory;
import za.ac.cput.repository.impl.PatientRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PatientRepositoryImplTest {

    private static PatientRepositoryImpl repository;
    private static Patient patient;

    @BeforeAll
    static void setUp() throws Exception {
        java.lang.reflect.Field field =
                PatientRepositoryImpl.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, null);

        repository = PatientRepositoryImpl.getInstance();

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
    void testSingleton_SameInstance() {
        PatientRepositoryImpl instance1 = PatientRepositoryImpl.getInstance();
        PatientRepositoryImpl instance2 = PatientRepositoryImpl.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @Order(2)
    void testCreate_Success() {
        Patient created = repository.create(patient);
        assertNotNull(created);
        assertEquals(21, created.getPatientId());
        assertEquals("Aidan", created.getPatientName());
        System.out.println(created);
    }

    @Test
    @Order(3)
    void testCreate_Null_Fails() {
        Patient created = repository.create(null);
        assertNull(created);
    }

    @Test
    @Order(4)
    void testCreate_Duplicate_Fails() {
        Patient duplicate = repository.create(patient);
        assertNull(duplicate);
    }

    @Test
    @Order(5)
    void testRead_Success() {
        Patient read = repository.read(21);
        assertNotNull(read);
        assertEquals(21, read.getPatientId());
        System.out.println(read);
    }

    @Test
    @Order(6)
    void testRead_NonExistent_Fails() {
        Patient read = repository.read(999);
        assertNull(read);
    }

    @Test
    @Order(7)
    void testRead_InvalidId_Fails() {
        Patient read = repository.read(0);
        assertNull(read);
    }

    @Test
    @Order(8)
    void testUpdate_Success() {
        Patient updated = new Patient.Builder()
                .copy(patient)
                .setPatientName("UpdatedName")
                .build();
        Patient result = repository.update(updated);
        assertNotNull(result);
        assertEquals("UpdatedName", result.getPatientName());
        System.out.println(result);
    }

    @Test
    @Order(9)
    void testUpdate_NonExistent_Fails() {
        Patient nonExistent = PatientFactory.createPatient(
                99, "Ghost", "Patient", "0821234599",
                "ghost@cput.ac.za", LocalDate.of(2000, 1, 1));
        Patient result = repository.update(nonExistent);
        assertNull(result);
    }

    @Test
    @Order(10)
    void testUpdate_Null_Fails() {
        Patient result = repository.update(null);
        assertNull(result);
    }

    @Test
    @Order(11)
    void testGetAll_NotEmpty() {
        List<Patient> all = repository.getAll();
        assertNotNull(all);
        assertFalse(all.isEmpty());
        System.out.println(all);
    }

    @Test
    @Order(12)
    void testGetAll_ContainsCreatedPatient() {
        List<Patient> all = repository.getAll();
        assertTrue(all.stream()
                .anyMatch(p -> p.getPatientId() == patient.getPatientId()));
    }

    @Test
    @Order(13)
    void testGetAll_IsDefensiveCopy() {
        List<Patient> listA = repository.getAll();
        List<Patient> listB = repository.getAll();
        assertNotSame(listA, listB);
    }

    @Test
    @Order(14)
    void testDelete_Success() {
        boolean deleted = repository.delete(21);
        assertTrue(deleted);
        assertNull(repository.read(21));
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