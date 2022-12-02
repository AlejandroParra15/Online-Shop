package co.edu.icesi.OnlineShop.api;

import co.edu.icesi.OnlineShop.model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/shop/books")
public interface ItemAPI {
    @GetMapping
    public List<Item> getItems();

    @GetMapping("/{itemId}")
    public ResponseEntity<?> getItem(@PathVariable UUID itemId);

    @PostMapping()
    public ResponseEntity<?> createItem(@Valid @RequestBody Item item, BindingResult result);

    @PutMapping("/{itemId}")
    ResponseEntity<?> update(@Valid @RequestBody Item item, BindingResult result, @PathVariable UUID itemId);

    @DeleteMapping("/{itemId}")
    ResponseEntity<?> delete(@PathVariable UUID itemId);
}
