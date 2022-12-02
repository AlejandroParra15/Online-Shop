package co.edu.icesi.OnlineShop.service;

import co.edu.icesi.OnlineShop.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface UserService {


    public User getUser(@PathVariable UUID userId);

    public User createUser(@RequestBody User user);

    public List<User> getUsers();

    public void deleteUser(@PathVariable UUID userId);


}
