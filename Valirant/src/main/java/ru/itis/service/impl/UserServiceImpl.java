package ru.itis.service.impl;

import org.mindrot.jbcrypt.BCrypt;
import ru.itis.dto.UserDTO;
import ru.itis.models.User;
import ru.itis.repositories.UserRepositories;
import ru.itis.repositories.impl.UserRepositoriesImpl;
import ru.itis.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserRepositories userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoriesImpl();
    }

    @Override
    public User registerUser(String name, String email, String password) throws SQLException {
        if (userRepository.isEmailExist(email)) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует!");
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = User.builder()
                .name(name)
                .email(email)
                .password(hashedPassword)
                .role("user")
                .rating(0.0)
                .build();

        userRepository.save(user);
        return user;
    }

    @Override
    public UserDTO loginUser(String email, String password) throws SQLException {
        User user = userRepository.getUserByEmail(email);

        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            throw new IllegalArgumentException("Неверный email или пароль!");
        }

        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .rating(user.getRating())
                .build();
    }

    @Override
    public List<UserDTO> getAllUsers() throws SQLException {
        List<User> users = userRepository.getAll();

        return users.stream()
                .map(user -> UserDTO.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .rating(user.getRating())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public boolean isEmailExist(String email) throws SQLException {
        return userRepository.isEmailExist(email);
    }
}
