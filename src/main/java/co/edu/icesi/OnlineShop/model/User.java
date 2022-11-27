package co.edu.icesi.OnlineShop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;


@Data
@Table(name = "user", schema = "jpa_example")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
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

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
