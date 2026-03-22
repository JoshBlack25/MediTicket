/* PaymentRepositoryImpl.java
   Implementation of the Payment repository using an in-memory store
   Author: Abdullahi (your student number)
   Date: 22 March 2026
*/
package za.ac.cput.repository.impl;

import za.ac.cput.domain.Payment;
import za.ac.cput.repository.IPaymentRepository;

import java.util.HashMap;
import java.util.Map;

public class PaymentRepositoryImpl implements IPaymentRepository {

    // Singleton instance
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
        store.put(payment.getPaymentId(), payment);
        return payment;
    }

    @Override
    public Payment read(Integer id) {
        return store.get(id);
    }

    @Override
    public Payment update(Payment payment) {
        if (store.containsKey(payment.getPaymentId())) {
            store.put(payment.getPaymentId(), payment);
            return payment;
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (store.containsKey(id)) {
            store.remove(id);
            return true;
        }
        return false;
    }
}
