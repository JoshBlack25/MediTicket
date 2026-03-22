/* PatientTicketRepositoryImpl.java
   Implementation of IPatientTicketRepository using HashMap
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
        store.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    @Override
    public PatientTicket read(Integer id) {
        return store.get(id);
    }

    @Override
    public PatientTicket update(PatientTicket ticket) {
        if (store.containsKey(ticket.getTicketId())) {
            store.put(ticket.getTicketId(), ticket);
            return ticket;
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return store.remove(id) != null;
    }

    @Override
    public List<PatientTicket> getAll() {
        return new ArrayList<>(store.values());
    }
}