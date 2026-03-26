package com.uade.tpo.marketplace.repository;

import com.uade.tpo.marketplace.model.Role;
import com.uade.tpo.marketplace.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    private final List<User> users = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public UserRepository() {
        save(new User(null, "Administrador", "admin@marketplace.com", Role.ADMIN));
        save(new User(null, "Juan Profesor", "juan@marketplace.com", Role.TEACHER));
        save(new User(null, "Ana Alumna", "ana@marketplace.com", Role.STUDENT));
    }

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(nextId.getAndIncrement());
            users.add(user);
            return user;
        }

        deleteById(user.getId());
        users.add(user);
        return user;
    }

    public void deleteById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
