// Raul Everts 230270565
package za.ac.cput.repository.impl;

import za.ac.cput.domain.NotificationService;
import za.ac.cput.repository.INotificationRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationRepositoryImpl implements INotificationRepository {

    private static NotificationRepositoryImpl instance;
    private final Map<Integer, NotificationService> store = new HashMap<>();

    private NotificationRepositoryImpl() {}

    public static NotificationRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new NotificationRepositoryImpl();
        }
        return instance;
    }

    @Override
    public NotificationService create(NotificationService notification) {
        if (notification == null) return null;
        if (store.containsKey(notification.getNotificationId())) return null;
        store.put(notification.getNotificationId(), notification);
        return notification;
    }

    @Override
    public NotificationService read(Integer id) {
        if (id == null || id <= 0) return null;
        return store.get(id);
    }

    @Override
    public NotificationService update(NotificationService notification) {
        if (notification == null) return null;
        if (!store.containsKey(notification.getNotificationId())) return null;
        store.put(notification.getNotificationId(), notification);
        return notification;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null || id <= 0) return false;
        if (!store.containsKey(id)) return false;
        store.remove(id);
        return true;
    }

    @Override
    public List<NotificationService> getAll() {
        return new ArrayList<>(store.values());
    }
}