package co.edu.icesi.OnlineShop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Table(name = "user")
@Entity
@Builder
@AllArgsConstructor
public class User {

    public User() {
        this.orders = new ArrayList<>();
    }

    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "user_id")
    private UUID id;

    @Email(message ="must enter a valid email")
    @Column(name = "email")
    private String email;

    @NotEmpty(message ="can not be empty")
    @Size(min=8, max=50, message="must be between 8 and 50 characters")
    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonIgnoreProperties({"cliente","hibernateLazyInitializer","handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
