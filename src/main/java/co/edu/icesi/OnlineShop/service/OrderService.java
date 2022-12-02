package co.edu.icesi.OnlineShop.service;

import co.edu.icesi.OnlineShop.model.Order;

import java.util.UUID;

public interface OrderService {

    public Order findById(UUID id);

    public Order saveOrder(Order order);

    public void deleteById(UUID id);
}
