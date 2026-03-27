// Matthew Barron 230398863
package za.ac.cput.repository.impl;

import za.ac.cput.domain.ClinicStaff;
import za.ac.cput.repository.IClinicStaffRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClinicStaffRepositoryImpl implements IClinicStaffRepository {

    private static ClinicStaffRepositoryImpl instance;
    private final Map<Integer, ClinicStaff> store = new HashMap<>();

    private ClinicStaffRepositoryImpl() {}

    public static ClinicStaffRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ClinicStaffRepositoryImpl();
        }
        return instance;
    }

    @Override
    public ClinicStaff create(ClinicStaff staff) {
        if (staff == null) return null;
        if (store.containsKey(staff.getStaffId())) return null;
        store.put(staff.getStaffId(), staff);
        return staff;
    }

    @Override
    public ClinicStaff read(Integer id) {
        if (id == null || id <= 0) return null;
        return store.get(id);
    }

    @Override
    public ClinicStaff update(ClinicStaff staff) {
        if (staff == null) return null;
        if (!store.containsKey(staff.getStaffId())) return null;
        store.put(staff.getStaffId(), staff);
        return staff;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null || id <= 0) return false;
        if (!store.containsKey(id)) return false;
        store.remove(id);
        return true;
    }

    @Override
    public List<ClinicStaff> getAll() {
        return new ArrayList<>(store.values());
    }
}