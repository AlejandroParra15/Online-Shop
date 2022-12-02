package co.edu.icesi.OnlineShop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import co.edu.icesi.OnlineShop.api.UserAPI;
import co.edu.icesi.OnlineShop.model.User;
import co.edu.icesi.OnlineShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@AllArgsConstructor
public class UserController implements UserAPI {

	@Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @Override
    public ResponseEntity<?> getUser(UUID userId) {
        User user;
        Map<String, Object> response = new HashMap<>();

        try {
            user = userService.getUser(userId);
        } catch(DataAccessException e) {
            response.put("message", "Error when querying the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(user == null) {
            response.put("message", "The user with the ID: ".concat(userId.toString().concat(" does not exist in the database!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createUser(User user, BindingResult result) {
        User userNew = null;
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
            userNew = user;
            userNew.setPassword(passwordEncoder.encode(user.getPassword()));
            userNew = userService.createUser(userNew);
        } catch(DataAccessException e) {
            response.put("message", "Error when inserting into the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "User has been successfully created!");
        response.put("animal", userNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(User user, BindingResult result, UUID userId) {
        User userActual = userService.getUser(userId);

        User userUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "Field '" + err.getField() +"' "+ err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (userActual == null) {
            response.put("message", "Error: could not edit the user with the ID:"
                    .concat(userId.toString().concat(" does not exist in the database!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            userActual.setEmail(user.getEmail());
            userActual.setPassword(user.getPassword());
            userActual.setAddress(user.getAddress());
            userActual.setPhoneNumber(user.getPhoneNumber());


            userUpdated = userService.createUser(userActual);

        } catch (DataAccessException e) {
            response.put("message", "Error updating the user in the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "The user has been successfully updated!");
        response.put("user", userUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> delete(UUID userId) {
        Map<String, Object> response = new HashMap<>();

        try {
            userService.deleteUser(userId);
        } catch (DataAccessException e) {
            response.put("message", "Error removing the user from the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "User removed successfully!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
