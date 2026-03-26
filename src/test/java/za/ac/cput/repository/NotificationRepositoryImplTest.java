package za.ac.cput.repository;

import za.ac.cput.domain.enums.NotificationType;
import za.ac.cput.domain.NotificationService;
import za.ac.cput.factory.NotificationFactory;
import za.ac.cput.repository.impl.NotificationRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//Raul Everts 230270565
public class NotificationRepositoryImplTest {

    private NotificationRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new NotificationRepositoryImpl();
    }

    @Test
    void testCreate() {
        NotificationService notification = NotificationFactory.createSMS(1, null, null, null);
        NotificationService saved = repository.create(notification);
        assertNotNull(saved);
        assertEquals(1, saved.getNotificationId());
    }

    @Test
    void testRead() {
        NotificationService notification = NotificationFactory.createSMS(2, null, null, null);
        repository.create(notification);
        NotificationService found = repository.read(2);
        assertNotNull(found);
        assertEquals(2, found.getNotificationId());
    }

    @Test
    void testUpdate() {
        NotificationService notification = NotificationFactory.createSMS(6, null, null, null);
        repository.create(notification);
        NotificationService updated = NotificationFactory.createEMAIL(6, null, null, null);
        repository.update(updated);
        NotificationService found = repository.read(6);
        assertNotNull(found);
        assertEquals(NotificationType.EMAIL, found.getNotificationType());
    }

    @Test
    void testDelete() {
        NotificationService notification = NotificationFactory.createSMS(3, null, null, null);
        repository.create(notification);
        boolean result = repository.delete(3);
        assertTrue(result);
        assertNull(repository.read(3));
    }

    @Test
    void testGetAll() {
        NotificationService n1 = NotificationFactory.createSMS(4, null, null, null);
        NotificationService n2 = NotificationFactory.createEMAIL(5, null, null, null);
        repository.create(n1);
        repository.create(n2);
        assertEquals(2, repository.getAll().size());
    }
}
