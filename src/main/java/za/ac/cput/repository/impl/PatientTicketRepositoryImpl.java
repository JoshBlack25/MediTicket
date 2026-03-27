/* PatientTicketRepositoryImpl.java
   Author: Joshua A (230317693)
   Date: 22 March 2026
*/
package za.ac.cput.repository.impl;

import za.ac.cput.domain.PatientTicket;
import za.ac.cput.repository.IPatientTicketRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientTicketRepositoryImpl implements IPatientTicketRepository {

    private static PatientTicketRepositoryImpl instance;
    private final Map<Integer, PatientTicket> store = new HashMap<>();

    private PatientTicketRepositoryImpl() {}

    public static PatientTicketRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new PatientTicketRepositoryImpl();
        }
        return instance;
    }

    @Override
    public PatientTicket create(PatientTicket ticket) {
        if (ticket == null) return null;
        if (store.containsKey(ticket.getTicketId())) return null;
        store.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    @Override
    public PatientTicket read(Integer id) {
        if (id == null || id <= 0) return null;
        return store.get(id);
    }

    @Override
    public PatientTicket update(PatientTicket ticket) {
        if (ticket == null) return null;
        if (!store.containsKey(ticket.getTicketId())) return null;
        store.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null || id <= 0) return false;
        if (!store.containsKey(id)) return false;
        store.remove(id);
        return true;
    }

    @Override
    public List<PatientTicket> getAll() {
        return new ArrayList<>(store.values());
    }
}