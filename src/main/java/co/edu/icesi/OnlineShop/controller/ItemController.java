package co.edu.icesi.OnlineShop.controller;

import co.edu.icesi.OnlineShop.api.ItemAPI;
import co.edu.icesi.OnlineShop.model.Item;
import co.edu.icesi.OnlineShop.model.User;
import co.edu.icesi.OnlineShop.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@AllArgsConstructor
public class ItemController implements ItemAPI {

    @Autowired
    ItemService itemService;

    @Override
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @Override
    public ResponseEntity<?> getItem(UUID itemId) {
        Item item;
        Map<String, Object> response = new HashMap<>();

        try {
            item = itemService.getItem(itemId);
        } catch(DataAccessException e) {
            response.put("message", "Error when querying the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(item == null) {
            response.put("message", "The item with the ID: ".concat(itemId.toString().concat(" does not exist in the database!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createItem(Item item, BindingResult result) {
        Item itemNew = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "Field '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            itemNew = itemService.createItem(item);
        } catch(DataAccessException e) {
            response.put("message", "Error when inserting into the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Item has been successfully created!");
        response.put("item", itemNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(Item item, BindingResult result, UUID itemId) {
        Item itemActual = itemService.getItem(itemId);

        Item itemUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "Field '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (itemActual == null) {
            response.put("message", "Error: could not edit the item with the ID:"
                    .concat(itemId.toString().concat(" does not exist in the database!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            itemActual.setName(item.getName());
            itemActual.setDescription(item.getDescription());
            itemActual.setPrice(item.getPrice());
            itemActual.setImage(item.getImage());


            itemUpdated = itemService.createItem(itemActual);

        } catch (DataAccessException e) {
            response.put("message", "Error updating the item in the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "The item has been successfully updated!");
        response.put("item", itemUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> delete(UUID itemId) {

        Map<String, Object> response = new HashMap<>();

        try {
            itemService.deleteItem(itemId);
        } catch (DataAccessException e) {
            response.put("message", "Error removing the item from the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Item removed successfully!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
