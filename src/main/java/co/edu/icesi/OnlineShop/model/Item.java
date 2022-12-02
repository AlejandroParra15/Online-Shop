package co.edu.icesi.OnlineShop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "item_id")
    private UUID itemId;

    @NotEmpty(message ="can not be empty")
    private String name;

    @NotEmpty(message ="can not be empty")
    private String description;

    @NotEmpty(message ="can not be empty")
    private String image;

    @NotNull(message ="can not be empty")
    private double price;

    @PrePersist
    public void generateId(){
        this.itemId = UUID.randomUUID();
    }

}
