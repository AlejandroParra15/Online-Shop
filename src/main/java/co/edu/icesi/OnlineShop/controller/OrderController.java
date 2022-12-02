package co.edu.icesi.OnlineShop.controller;

import co.edu.icesi.OnlineShop.api.OrderAPI;
import co.edu.icesi.OnlineShop.model.Order;
import co.edu.icesi.OnlineShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class OrderController implements OrderAPI {

    @Autowired
    OrderService orderService;

    @Override
    public Order show(UUID id) {
        return orderService.findById(id);
    }
}
