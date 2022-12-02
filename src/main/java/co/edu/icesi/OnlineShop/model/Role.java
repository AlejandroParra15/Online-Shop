package co.edu.icesi.OnlineShop.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "role_id")
    private UUID id;


    @Column(unique = true,name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
