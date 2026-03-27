/* AppointmentFactoryTest.java
Appointment Model Class
Author: Joshua Peter Bonzet (221312536)
Date: 26 March 2026
*/

package za.ac.cput.factory;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppointmentFactoryTest {

    private static Doctor doctor;
    private static ClinicStaff staff;

    @BeforeAll
    static void setUp() {
        doctor = new Doctor.Builder()
                .setDoctorId(1)
                .setDoctorName("Dr. John Smith")
                .build();

        staff = new ClinicStaff.Builder()
                .setStaffId(1)
                .setStaffName("Jane Doe")
                .build();
    }

    @Test
    @Order(1)
    void testCreateAppointment_Success() {
        Appointment appointment = AppointmentFactory.createAppointment(
                1,
                LocalDate.of(2026, 4, 10),
                LocalTime.of(9, 0),
                ConfirmationStatus.CONFIRMED,
                doctor,
                staff
        );
        assertNotNull(appointment);
        assertEquals(1, appointment.getAppointmentId());
        assertEquals(LocalDate.of(2026, 4, 10), appointment.getAppointmentDate());
        assertEquals(LocalTime.of(9, 0), appointment.getAppointmentTime());
        assertEquals(ConfirmationStatus.CONFIRMED, appointment.getConfirmationStatus());
        assertNotNull(appointment.getDoctor());
        assertNotNull(appointment.getStaff());
    }

    @Test
    @Order(2)
    void testCreateAppointment_InvalidId_Fails() {
        Appointment appointment = AppointmentFactory.createAppointment(
                0,
                LocalDate.of(2026, 4, 10),
                LocalTime.of(9, 0),
                ConfirmationStatus.CONFIRMED,
                doctor,
                staff
        );
        assertNull(appointment);
    }

    @Test
    @Order(3)
    void testCreateAppointment_NegativeId_Fails() {
        Appointment appointment = AppointmentFactory.createAppointment(
                -1,
                LocalDate.of(2026, 4, 10),
                LocalTime.of(9, 0),
                ConfirmationStatus.CONFIRMED,
                doctor,
                staff
        );
        assertNull(appointment);
    }

    @Test
    @Order(4)
    void testCreateAppointment_NullDate_Fails() {
        Appointment appointment = AppointmentFactory.createAppointment(
                2,
                null,
                LocalTime.of(9, 0),
                ConfirmationStatus.CONFIRMED,
                doctor,
                staff
        );
        assertNull(appointment);
    }

    @Test
    @Order(5)
    void testCreateAppointment_NullTime_Fails() {
        Appointment appointment = AppointmentFactory.createAppointment(
                3,
                LocalDate.of(2026, 4, 10),
                null,
                ConfirmationStatus.CONFIRMED,
                doctor,
                staff
        );
        assertNull(appointment);
    }

    @Test
    @Order(6)
    void testCreateAppointment_NullConfirmationStatus_Fails() {
        Appointment appointment = AppointmentFactory.createAppointment(
                4,
                LocalDate.of(2026, 4, 10),
                LocalTime.of(9, 0),
                null,
                doctor,
                staff
        );
        assertNull(appointment);
    }

    @Test
    @Order(7)
    void testCreateAppointment_NullDoctor_Fails() {
        Appointment appointment = AppointmentFactory.createAppointment(
                5,
                LocalDate.of(2026, 4, 10),
                LocalTime.of(9, 0),
                ConfirmationStatus.CONFIRMED,
                null,
                staff
        );
        assertNull(appointment);
    }

    @Test
    @Order(8)
    void testCreateAppointment_NullStaff_Fails() {
        Appointment appointment = AppointmentFactory.createAppointment(
                6,
                LocalDate.of(2026, 4, 10),
                LocalTime.of(9, 0),
                ConfirmationStatus.CONFIRMED,
                doctor,
                null
        );
        assertNull(appointment);
    }

    @Test
    @Order(9)
    void testCreateAppointment_AllNull_Fails() {
        Appointment appointment = AppointmentFactory.createAppointment(
                0,
                null,
                null,
                null,
                null,
                null
        );
        assertNull(appointment);
    }
}