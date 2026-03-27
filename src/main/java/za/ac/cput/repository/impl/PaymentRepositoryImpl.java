/* PaymentRepositoryImpl.java
   Author: Abdullahi (230971091)
   Date: 22 March 2026
*/
package za.ac.cput.repository.impl;

import za.ac.cput.domain.Payment;
import za.ac.cput.repository.IPaymentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentRepositoryImpl implements IPaymentRepository {

    private static PaymentRepositoryImpl instance;
    private final Map<Integer, Payment> store = new HashMap<>();

    private PaymentRepositoryImpl() {}

    public static PaymentRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new PaymentRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Payment create(Payment payment) {
        if (payment == null) return null;
        if (store.containsKey(payment.getPaymentId())) return null;
        store.put(payment.getPaymentId(), payment);
        return payment;
    }

    @Override
    public Payment read(Integer id) {
        if (id == null || id <= 0) return null;
        return store.get(id);
    }

    @Override
    public Payment update(Payment payment) {
        if (payment == null) return null;
        if (!store.containsKey(payment.getPaymentId())) return null;
        store.put(payment.getPaymentId(), payment);
        return payment;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null || id <= 0) return false;
        if (!store.containsKey(id)) return false;
        store.remove(id);
        return true;
    }

    @Override
    public List<Payment> getAll() {
        return new ArrayList<>(store.values());
    }
}