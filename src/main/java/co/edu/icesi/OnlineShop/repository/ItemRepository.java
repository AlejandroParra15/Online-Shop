package co.edu.icesi.OnlineShop.repository;

import co.edu.icesi.OnlineShop.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ItemRepository extends CrudRepository<Item, UUID> {
}
