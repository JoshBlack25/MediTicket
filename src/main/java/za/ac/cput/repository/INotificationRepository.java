package za.ac.cput.repository;

import za.ac.cput.domain.NotificationService;
import java.util.List;
//needs to extend irepo
public interface INotificationRepository {
    NotificationService create(NotificationService notification);
    NotificationService read(int notificationId);
    NotificationService update(NotificationService notification);
    void delete(int notificationId);
    List<NotificationService> getAll();
}