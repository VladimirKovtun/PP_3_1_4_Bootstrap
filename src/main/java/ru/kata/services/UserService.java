package ru.kata.services;

import ru.kata.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(User user);

    List<User> showAllUsers();

    User getUser(Long id);

    void editUser(User user);

    void removeUser(Long id);

    Optional<User> findByEmail(String name);

}
