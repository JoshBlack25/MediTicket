package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Patient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
final
class PatientFactoryTest {

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = PatientFactory.createPatient(
                021,
                "Aidan",
                "Barends",
                "0712345678",
                "aidanbarends@cput.ac.za",
                LocalDate.of(2004, 11, 10)
        );
    }

    @Test
    void a_createPatient() {
        assertNotNull(patient);
        System.out.println(patient.toString());// we check if the patient was created
    }

    @Test
    void b_getPatientId(){
        assertEquals(021, patient.getPatientId());
    }

    @Test
    void c_getPatientName(){
        assertEquals("Aidan", patient.getPatientName());
    }

    @Test
    void d_getPatientSurname(){
        assertEquals("Barends", patient.getPatientSurname());
    }

    @Test
    void e_getPatientCell(){
        assertEquals("0712345678", patient.getPatientCell());
    }

    @Test
    void f_getPatientEmail(){
        assertEquals("aidanbarends@cput.ac.za", patient.getPatientEmail());
    }

    @Test
    void g_getPatientDOB(){
        assertEquals(LocalDate.of(2004,11,10), patient.getPatientDOB());
    }
}
