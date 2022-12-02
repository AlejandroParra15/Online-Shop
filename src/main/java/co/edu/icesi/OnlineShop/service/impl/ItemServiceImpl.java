package co.edu.icesi.OnlineShop.service.impl;

import co.edu.icesi.OnlineShop.model.Item;
import co.edu.icesi.OnlineShop.repository.ItemRepository;
import co.edu.icesi.OnlineShop.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    @Transactional(readOnly = true)
    public Item getItem(UUID itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    @Override
    @Transactional()
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getItems() {
        return StreamSupport.stream(itemRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    @Transactional()
    public void deleteItem(UUID itemId) {
        itemRepository.deleteById(itemId);
    }
}
