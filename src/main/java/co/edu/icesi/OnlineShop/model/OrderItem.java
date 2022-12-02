package co.edu.icesi.OnlineShop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "order_item_id")
    private UUID orderItemId;

    @NotNull(message ="can not be empty")
    private int quantity;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public double calculatePrice(){
        return quantity*item.getPrice();
    }

    @PrePersist
    public void generateId(){
        this.orderItemId = UUID.randomUUID();
    }
}
