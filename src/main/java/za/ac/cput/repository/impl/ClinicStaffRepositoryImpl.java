package za.ac.cput.repository.impl;

import za.ac.cput.domain.ClinicStaff;
import za.ac.cput.repository.IClinicStaffRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClinicStaffRepositoryImpl implements IClinicStaffRepository {

    private Map<Integer, ClinicStaff> db = new HashMap<>();

    @Override
    public ClinicStaff create(ClinicStaff staff) {
        db.put(staff.getStaffId(), staff);
        return staff;
    }

    @Override
    public ClinicStaff read(Integer id) {
        return db.get(id);
    }

    @Override
    public ClinicStaff update(ClinicStaff staff) {
        db.put(staff.getStaffId(), staff);
        return staff;
    }

    @Override
    public boolean delete(Integer id) {
        return db.remove(id) != null;
    }

    @Override
    public List<ClinicStaff> getAll() {
        return new ArrayList<>(db.values());
    }
}
