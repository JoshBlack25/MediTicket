/* TicketStatusRepositoryImpl.java
   Implementation of ITicketStatusRepository using HashMap
   Author: Joshua A (230317693)
   Date: 22 March 2026
*/

package za.ac.cput.repository.impl;

import za.ac.cput.domain.TicketStatus;
import za.ac.cput.repository.ITicketStatusRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketStatusRepositoryImpl implements ITicketStatusRepository {
    private static TicketStatusRepositoryImpl instance;
    private final Map<Integer, TicketStatus> store = new HashMap<>();

    private TicketStatusRepositoryImpl() {}

    public static TicketStatusRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new TicketStatusRepositoryImpl();
        }
        return instance;
    }

    @Override
    public TicketStatus create(TicketStatus status) {
        store.put(status.getStatusId(), status);
        return status;
    }

    @Override
    public TicketStatus read(Integer id) {
        return store.get(id);
    }

    @Override
    public TicketStatus update(TicketStatus status) {
        if (store.containsKey(status.getStatusId())) {
            store.put(status.getStatusId(), status);
            return status;
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return store.remove(id) != null;
    }

    @Override
    public List<TicketStatus> getAll() {
        return new ArrayList<>(store.values());
    }
}
