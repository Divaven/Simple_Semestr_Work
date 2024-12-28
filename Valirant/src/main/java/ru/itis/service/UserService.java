package ru.itis.service;

import ru.itis.dto.UserDTO;
import ru.itis.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User registerUser(String name, String email, String password) throws SQLException;

    UserDTO loginUser(String email, String password) throws SQLException;

    List<UserDTO> getAllUsers() throws SQLException;

    boolean isEmailExist(String email) throws SQLException;
}
