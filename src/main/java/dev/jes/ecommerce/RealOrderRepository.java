package dev.jes.ecommerce;

import java.util.Map;

// Implementação real do repositório de pedidos
public class RealOrderRepository implements OrderRepository {

    private Map<Integer, Order> orders;

    public RealOrderRepository(Map<Integer, Order> orders) {
        this.orders = orders;
    }

    @Override
    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    @Override
    public Order findById(int id) {
        return orders.get(id);
    }
}
