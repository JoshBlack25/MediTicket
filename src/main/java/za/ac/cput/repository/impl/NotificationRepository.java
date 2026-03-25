package za.ac.cput.repository.impl;

import za.ac.cput.domain.NotificationService;
import za.ac.cput.repository.INotificationRepository;
import java.util.ArrayList;
import java.util.List;

public class NotificationRepository implements INotificationRepository {
    private List<NotificationService> notifications = new ArrayList<>();

    @Override
    public NotificationService create(NotificationService notification) {
        notifications.add(notification);
        return notification;
    }

    @Override
    public NotificationService read(int notificationId) {
        for (NotificationService n : notifications) {
            if (n.getNotificationId() == notificationId) return n;
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
    public void delete(int notificationId) {
        notifications.removeIf(n -> n.getNotificationId() == notificationId);
    }

    @Override
    public List<NotificationService> getAll() {
        return notifications;
    }
}