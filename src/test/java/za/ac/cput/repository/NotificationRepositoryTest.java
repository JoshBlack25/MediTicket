package za.ac.cput.repository;

import za.ac.cput.domain.NotificationService;
import za.ac.cput.factory.NotificationFactory;
import za.ac.cput.repository.impl.NotificationRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotificationRepositoryTest {

    private NotificationRepository repository = new NotificationRepository();

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
    void testDelete() {
        NotificationService notification = NotificationFactory.createSMS(3, null, null, null);
        repository.create(notification);
        repository.delete(3);
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