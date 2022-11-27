package co.edu.icesi.OnlineShop.repository;

import co.edu.icesi.OnlineShop.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
	
}
