package za.ac.cput.factory;

import za.ac.cput.domain.NotificationService;
import za.ac.cput.domain.enums.NotificationType;
import za.ac.cput.domain.enums.NotificationStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//Raul Everts 230270565
public class NotificationFactoryTest {

    @Test
    void testCreateSMS() {
        NotificationService notification = NotificationFactory.createSMS(1, null, null, null);
        assertNotNull(notification);
        assertEquals(NotificationType.SMS, notification.getNotificationType());
        assertEquals(NotificationStatus.PENDING, notification.getNotificationStatus());
    }

    @Test
    void testCreateEMAIL() {
        NotificationService notification = NotificationFactory.createEMAIL(2, null, null, null);
        assertNotNull(notification);
        assertEquals(NotificationType.EMAIL, notification.getNotificationType());
        assertEquals(NotificationStatus.PENDING, notification.getNotificationStatus());
    }
}