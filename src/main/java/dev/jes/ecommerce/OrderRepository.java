package dev.jes.ecommerce;

// Interface de repositório de pedidos
public interface OrderRepository {

    void save(Order order);

    Order findById(int id);
}
