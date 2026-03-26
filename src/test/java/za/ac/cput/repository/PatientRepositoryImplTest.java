// 230255639 - Aidan Barends
// Date Completed: 26 March

package za.ac.cput.repository;

import za.ac.cput.domain.Patient;
import za.ac.cput.factory.PatientFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import za.ac.cput.repository.impl.PatientRepositoryImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
class PatientRepositoryImplTest {

    private IPatientRepository repository;
    private Patient patient;

    @BeforeEach
    void setUp() {
        repository = PatientRepositoryImpl.getRepository();
        patient = PatientFactory.createPatient(
                21,
                "Aidan",
                "Barends",
                "0712345678",
                "aidanbarends@cput.ac.za",
                LocalDate.of(2004, 11, 10)
        );
        repository.create(patient); // Add patient before tests
    }

    @Test
    void a_create() {
        Patient newPatient = PatientFactory.createPatient(
                22,
                "Idris",
                "Adringo",
                "0723456789",
                "idrisAdringo@cput.ac.za",
                LocalDate.of(2000, 1, 1)
        );

        Patient created = repository.create(newPatient);
        assertNotNull(created);
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        Patient read = repository.read(patient.getPatientId());
        assertNotNull(read);
        assertEquals(patient.getPatientId(), read.getPatientId());
        System.out.println("Expected: " + patient);
        System.out.println("Actual: " + read);
    }

    @Test
    void c_update() {
        Patient updated = new Patient.Builder()
                .copy(patient)
                .setPatientName("UpdatedName")
                .build();

        Patient result = repository.update(updated);
        assertNotNull(result);
        assertEquals("UpdatedName", result.getPatientName());
        System.out.println("Updated: " + result);
    }

    @Test
    void d_delete() {
        boolean deleted = repository.delete(patient.getPatientId());
        assertTrue(deleted);
        System.out.println("Deleted: " + deleted);
    }

    @Test
    void e_getAll() {
        System.out.println("All Patients:");
        repository.getAll().forEach(System.out::println);
        assertTrue(repository.getAll().size() > 0);
    }
}