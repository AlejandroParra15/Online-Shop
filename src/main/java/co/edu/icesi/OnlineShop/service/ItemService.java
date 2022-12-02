package co.edu.icesi.OnlineShop.service;

import co.edu.icesi.OnlineShop.model.Item;
import co.edu.icesi.OnlineShop.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface ItemService {

    public Item getItem(@PathVariable UUID itemId);

    public Item createItem(@RequestBody Item item);

    public List<Item> getItems();

    public void deleteItem(@PathVariable UUID itemId);
}
