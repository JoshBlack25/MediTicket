// Matthew Barron 230398863
package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.ClinicStaff;
import za.ac.cput.factory.ClinicStaffFactory;
import za.ac.cput.repository.impl.ClinicStaffRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClinicStaffRepositoryImplTest {

    private static ClinicStaffRepositoryImpl repository;
    private static ClinicStaff staff;

    @BeforeAll
    static void setUp() throws Exception {
        java.lang.reflect.Field field =
                ClinicStaffRepositoryImpl.class.getDeclaredField("instance");
        field.setAccessible(true);
        field.set(null, null);

        repository = ClinicStaffRepositoryImpl.getInstance();

        staff = ClinicStaffFactory.createClinicStaff(
                1,
                "John",
                "Doe",
                "Nurse",
                "john.doe@example.com",
                "0821234567"
        );
    }

    @Test
    @Order(1)
    void testSingleton_SameInstance() {
        ClinicStaffRepositoryImpl instance1 = ClinicStaffRepositoryImpl.getInstance();
        ClinicStaffRepositoryImpl instance2 = ClinicStaffRepositoryImpl.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    @Order(2)
    void testCreate_Success() {
        ClinicStaff created = repository.create(staff);
        assertNotNull(created);
        assertEquals(1, created.getStaffId());
        assertEquals("John", created.getStaffName());
        System.out.println(created);
    }

    @Test
    @Order(3)
    void testCreate_Null_Fails() {
        ClinicStaff created = repository.create(null);
        assertNull(created);
    }

    @Test
    @Order(4)
    void testCreate_Duplicate_Fails() {
        ClinicStaff duplicate = repository.create(staff);
        assertNull(duplicate);
    }

    @Test
    @Order(5)
    void testRead_Success() {
        ClinicStaff read = repository.read(1);
        assertNotNull(read);
        assertEquals("John", read.getStaffName());
        System.out.println(read);
    }

    @Test
    @Order(6)
    void testRead_NonExistent_Fails() {
        ClinicStaff read = repository.read(999);
        assertNull(read);
    }

    @Test
    @Order(7)
    void testRead_InvalidId_Fails() {
        ClinicStaff read = repository.read(0);
        assertNull(read);
    }

    @Test
    @Order(8)
    void testUpdate_Success() {
        ClinicStaff updated = new ClinicStaff.Builder()
                .copy(staff)
                .setStaffName("Jane")
                .setStaffRole("Doctor")
                .build();
        ClinicStaff result = repository.update(updated);
        assertNotNull(result);
        assertEquals("Jane", result.getStaffName());
        assertEquals("Doctor", result.getStaffRole());
        System.out.println(result);
    }

    @Test
    @Order(9)
    void testUpdate_NonExistent_Fails() {
        ClinicStaff nonExistent = ClinicStaffFactory.createClinicStaff(
                99, "Ghost", "Staff", "Admin",
                "ghost@clinic.com", "0821234599");
        ClinicStaff result = repository.update(nonExistent);
        assertNull(result);
    }

    @Test
    @Order(10)
    void testUpdate_Null_Fails() {
        ClinicStaff result = repository.update(null);
        assertNull(result);
    }

    @Test
    @Order(11)
    void testGetAll_NotEmpty() {
        List<ClinicStaff> all = repository.getAll();
        assertNotNull(all);
        assertFalse(all.isEmpty());
        System.out.println(all);
    }

    @Test
    @Order(12)
    void testGetAll_IsDefensiveCopy() {
        List<ClinicStaff> listA = repository.getAll();
        List<ClinicStaff> listB = repository.getAll();
        assertNotSame(listA, listB);
    }

    @Test
    @Order(13)
    void testDelete_Success() {
        boolean deleted = repository.delete(1);
        assertTrue(deleted);
        assertNull(repository.read(1));
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