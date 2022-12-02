package co.edu.icesi.OnlineShop.service.impl;

import co.edu.icesi.OnlineShop.model.Order;
import co.edu.icesi.OnlineShop.repository.OrderRepository;
import co.edu.icesi.OnlineShop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional(readOnly = true)
    public Order findById(UUID id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        orderRepository.deleteById(id);
    }
}
