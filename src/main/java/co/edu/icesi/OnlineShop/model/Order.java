package co.edu.icesi.OnlineShop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "orders")
@AllArgsConstructor
public class Order {

    public Order(){
        items = new ArrayList<>();
    }
    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "order_id")
    private UUID orderId;

    @NotNull(message ="can not be empty")
    private double total;

    @NotEmpty
    private String status;

    @JsonIgnoreProperties({"orders","hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "order_item_id")
    List<OrderItem> items;

    public double calculateTotal(){
        double total = 0;
        for (OrderItem item: items) {
            total+=item.calculatePrice();
        }
        return total;
    }

    @PrePersist
    public void generateId(){
        this.orderId = UUID.randomUUID();
    }

}
