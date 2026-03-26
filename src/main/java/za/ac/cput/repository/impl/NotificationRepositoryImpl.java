// repository/impl/NotificationRepository.java
package za.ac.cput.repository.impl;

import za.ac.cput.domain.NotificationService;
import za.ac.cput.repository.INotificationRepository;
import java.util.ArrayList;
import java.util.List;
//Raul Everts 230270565
public class NotificationRepositoryImpl implements INotificationRepository {
    private List<NotificationService> notifications = new ArrayList<>();

    @Override
    public NotificationService create(NotificationService notification) {
        notifications.add(notification);
        return notification;
    }

    @Override
    public NotificationService read(Integer notificationId) {
        for (NotificationService n : notifications) {
            if (n.getNotificationId() == (int) notificationId) return n;
        }
        return null;
    }

    @Override
    public NotificationService update(NotificationService notification) {
        delete(notification.getNotificationId());
        notifications.add(notification);
        return notification;
    }

    @Override
    public boolean delete(Integer notificationId) {
        return notifications.removeIf(n -> n.getNotificationId() == notificationId);
    }

    @Override
    public List<NotificationService> getAll() {
        return notifications;
    }
}