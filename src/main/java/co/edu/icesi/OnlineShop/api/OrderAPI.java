package co.edu.icesi.OnlineShop.api;

import co.edu.icesi.OnlineShop.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@RequestMapping("/shop")
public interface OrderAPI {

    @GetMapping("/orders/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Order show(@PathVariable UUID id);


}
