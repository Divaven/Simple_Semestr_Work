package ru.itis.repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.itis.models.User;
import ru.itis.repositories.UserRepositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoriesImpl implements UserRepositories {
    private static final String URL = "jdbc:postgresql://localhost:5432/Valorant";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Arsen2005";

    private static final String INSERT_USER = "INSERT INTO users (name, role, password, email, rating) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USER = "UPDATE users SET name = ?, password = ?, email = ? WHERE id = ?";
    private static final String SELECT_USER = "SELECT * FROM users";
    private static final String SELECT_USER_BY_EMAIL = "SELECT id, name, role, password, email, rating FROM users WHERE email = ?";
    private static final String SELECT_USER_BY_ID = "SELECT name, email FROM users WHERE id = ?";
    private static final String SELECT_EMAIL_EXIST = "SELECT COUNT(*) FROM users WHERE email = ?";
    private static final String SELECT_TOP_USERS = "SELECT id FROM users FROM users ORDER BY rating DESC LIMIT 5";


    @Override
    public void save(User user) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getRole());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setDouble(5, user.getRating());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User user) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);

            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String role = resultSet.getString("role");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                double rating = resultSet.getDouble("rating");
                users.add(User.builder()
                        .id(id)
                        .name(name)
                        .role(role)
                        .email(email)
                        .password(password)
                        .rating(rating)
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public User getUserById(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                return User.builder()
                        .name(name)
                        .email(email)
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .role(resultSet.getString("role"))
                        .password(resultSet.getString("password"))
                        .email(resultSet.getString("email"))
                        .rating(resultSet.getDouble("rating"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean isEmailExist(String email) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMAIL_EXIST);

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<User> getTopUserWithHighestRating() {
        List<User> topUsers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOP_USERS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                topUsers.add(User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .role(resultSet.getString("role"))
                        .password(resultSet.getString("password"))
                        .email(resultSet.getString("email"))
                        .rating(resultSet.getDouble("rating"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return topUsers;
    }
}
