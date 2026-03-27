/* AppointmentRepositoryImplTest.java
   Author: Joshua Peter Bonzet (221312536)
   Date: 27 March 2026
*/
package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.*;
import za.ac.cput.domain.enums.ConfirmationStatus;
import za.ac.cput.factory.AppointmentFactory;
import za.ac.cput.repository.impl.AppointmentRepositoryImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppointmentRepositoryImplTest {

    private static AppointmentRepositoryImpl repository;
    private static Appointment appointment1;
    private static Appointment appointment2;
    private static Doctor doctor;
    private static ClinicStaff staff;

    @BeforeAll
    static void setUp() throws Exception {
        java.lang.reflect.Field field =
                AppointmentRepositoryImpl.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, null);

        repository = AppointmentRepositoryImpl.getInstance();

        doctor = new Doctor.Builder()
                .setDoctorId(1)
                .setDoctorName("John")
                .setDoctorSurname("Smith")
                .setDoctorSpecialty("General Medicine")
                .setDoctorCell("0821234567")
                .setDoctorEmail("john.smith@clinic.com")
                .build();

        staff = new ClinicStaff.Builder()
                .setStaffId(1)
                .setStaffName("Jane")
                .setStaffSurname("Doe")
                .setStaffRole("Receptionist")
                .setStaffEmail("jane.doe@clinic.com")
                .setStaffCell("0831234567")
                .build();

        appointment1 = AppointmentFactory.createAppointment(
                1,
                LocalDate.of(2026, 4, 10),
                LocalTime.of(9, 0),
                ConfirmationStatus.CONFIRMED,
                doctor,
                staff
        );

        appointment2 = AppointmentFactory.createAppointment(
                2,
                LocalDate.of(2026, 4, 15),
                LocalTime.of(14, 30),
                ConfirmationStatus.CONFIRMED,
                doctor,
                staff
        );
    }

    @Test
    @Order(1)
    void testSingleton_SameInstance() {
        AppointmentRepositoryImpl instance1 = AppointmentRepositoryImpl.getInstance();
        AppointmentRepositoryImpl instance2 = AppointmentRepositoryImpl.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @Order(2)
    void testCreate_Success() {
        Appointment created = repository.create(appointment1);
        assertNotNull(created);
        assertEquals(appointment1.getAppointmentId(), created.getAppointmentId());
        assertEquals(appointment1.getAppointmentDate(), created.getAppointmentDate());
        assertEquals(appointment1.getAppointmentTime(), created.getAppointmentTime());
        System.out.println(created);
    }

    @Test
    @Order(3)
    void testCreate_Null_Fails() {
        Appointment created = repository.create(null);
        assertNull(created);
    }

    @Test
    @Order(4)
    void testCreate_Duplicate_Fails() {
        Appointment duplicate = repository.create(appointment1);
        assertNull(duplicate);
    }

    @Test
    @Order(5)
    void testRead_Success() {
        Appointment read = repository.read(1);
        assertNotNull(read);
        assertEquals(1, read.getAppointmentId());
        System.out.println(read);
    }

    @Test
    @Order(6)
    void testRead_NonExistent_Fails() {
        Appointment read = repository.read(999);
        assertNull(read);
    }

    @Test
    @Order(7)
    void testRead_InvalidId_Fails() {
        Appointment read = repository.read(0);
        assertNull(read);
    }

    @Test
    @Order(8)
    void testUpdate_Success() {
        Appointment updated = AppointmentFactory.createAppointment(
                1,
                LocalDate.of(2026, 6, 20),
                LocalTime.of(11, 0),
                ConfirmationStatus.CONFIRMED,
                doctor,
                staff
        );
        Appointment result = repository.update(updated);
        assertNotNull(result);
        assertEquals(LocalDate.of(2026, 6, 20), result.getAppointmentDate());
        assertEquals(LocalTime.of(11, 0), result.getAppointmentTime());
        System.out.println(result);
    }

    @Test
    @Order(9)
    void testUpdate_NonExistent_Fails() {
        Appointment nonExistent = AppointmentFactory.createAppointment(
                999,
                LocalDate.of(2026, 7, 1),
                LocalTime.of(8, 0),
                ConfirmationStatus.CONFIRMED,
                doctor,
                staff
        );
        Appointment result = repository.update(nonExistent);
        assertNull(result);
    }

    @Test
    @Order(10)
    void testUpdate_Null_Fails() {
        Appointment result = repository.update(null);
        assertNull(result);
    }

    @Test
    @Order(11)
    void testGetAll_NotEmpty() {
        repository.create(appointment2);
        List<Appointment> all = repository.getAll();
        assertNotNull(all);
        assertFalse(all.isEmpty());
        System.out.println(all);
    }

    @Test
    @Order(12)
    void testGetAll_IsDefensiveCopy() {
        List<Appointment> listA = repository.getAll();
        List<Appointment> listB = repository.getAll();
        assertNotSame(listA, listB);
    }

    @Test
    @Order(13)
    void testDelete_Success() {
        boolean deleted = repository.delete(2);
        assertTrue(deleted);
        assertNull(repository.read(2));
    }

    @Test
    @Order(14)
    void testDelete_NonExistent_Fails() {
        boolean deleted = repository.delete(999);
        assertFalse(deleted);
    }

    @Test
    @Order(15)
    void testDelete_InvalidId_Fails() {
        boolean deleted = repository.delete(0);
        assertFalse(deleted);
    }
}