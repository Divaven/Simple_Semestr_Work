package ru.itis.repositories.impl;

import ru.itis.models.Problems;
import ru.itis.repositories.ProblemsRepositories;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProblemsRepositoriesImpl implements ProblemsRepositories {
    private static final String URL = "jdbc:postgresql://localhost:5432/Valorant";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Arsen2005";

    private static final String INSERT_PROBLEMS = "INSERT INTO problems (decription, answer, date) VALUES (?, ?, ?)";
    private static final String DELETE_PROBLEMS = "DELETE FROM problems WHERE id = ?";
    private static final String UPDATE_PROBLEMS = "UPDATE problems SET decription = ?, answer = ?, date = ? WHERE id = ?";
    private static final String SELECT_PROBLEMS = "SELECT * FROM problems";

    @Override
    public void save(Problems problems) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROBLEMS);

            preparedStatement.setString(1, problems.getDescription());
            preparedStatement.setString(2, problems.getAnswer());
            preparedStatement.setObject(3, problems.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Problems problems) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROBLEMS);

            preparedStatement.setInt(1, problems.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Problems problems) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROBLEMS);

            preparedStatement.setString(1, problems.getDescription());
            preparedStatement.setString(2, problems.getAnswer());
            preparedStatement.setObject(3, problems.getDate());
            preparedStatement.setInt(4, problems.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Problems> getAll() {
        List<Problems> problems = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROBLEMS);

             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                String answer = resultSet.getString("answer");
                LocalDate date = resultSet.getObject("date", LocalDate.class);
                problems.add(Problems.builder()
                        .id(id)
                        .description(description)
                        .answer(answer)
                        .date(date)
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return problems;
    }
}
