package co.edu.icesi.OnlineShop.api;

import co.edu.icesi.OnlineShop.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/shop/users")
public interface UserAPI {

    @GetMapping
    public List<User> getUsers();

    @GetMapping("/{userId}")
    public ResponseEntity<?>  getUser(@PathVariable UUID userId);

    @PostMapping()
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result);

    @PutMapping("/{userId}")
    ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable UUID userId);

    @DeleteMapping("/{userId}")
    ResponseEntity<?> delete(@PathVariable UUID userId);

}
