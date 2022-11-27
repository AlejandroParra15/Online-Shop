package co.edu.icesi.OnlineShop.service.impl;

import co.edu.icesi.OnlineShop.model.User;
import co.edu.icesi.OnlineShop.repository.UserRepository;
import co.edu.icesi.OnlineShop.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User getUser(UUID userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    @Transactional()
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
