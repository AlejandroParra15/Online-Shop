package co.edu.icesi.OnlineShop.repository;

import co.edu.icesi.OnlineShop.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID> {
}
