package ru.itis.repositories.impl;

import ru.itis.models.Problem_creating;
import ru.itis.repositories.Problem_CreatorsRepositories;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Problem_CreatorsRepositoriesImpl implements Problem_CreatorsRepositories {
    private static final String URL = "jdbc:postgresql://localhost:5432/Valorant";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Arsen2005";

    private static final String INSERT_PROBLEM_CREATING = "INSERT INTO problem_creating (problem_id, test_id) VALUES (?, ?)";
    private static final String DELETE_PROBLEM_CREATING = "DELETE FROM problem_creating WHERE id = ?";
    private static final String UPDATE_PROBLEM_CREATING = "UPDATE problem_creating SET problem_id = ?, test_id = ? WHERE id = ?";
    private static final String SELECT_PROBLEM_CREATING = "SELECT * FROM problem_creating";


    @Override
    public void save(Problem_creating problemCreating) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROBLEM_CREATING);

            preparedStatement.setInt(1, problemCreating.getProblem_id());
            preparedStatement.setInt(2, problemCreating.getTest_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Problem_creating problemCreating) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROBLEM_CREATING);

            preparedStatement.setInt(1, problemCreating.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Problem_creating problemCreating) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROBLEM_CREATING);

            preparedStatement.setInt(1, problemCreating.getTest_id());
            preparedStatement.setObject(2, problemCreating.getTest_id());
            preparedStatement.setInt(3, problemCreating.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Problem_creating> getAll() {
        List<Problem_creating> problemCreatings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROBLEM_CREATING);

             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int problem_id = resultSet.getInt("problem_id");
                int test_id = resultSet.getInt("test_id");
                problemCreatings.add(Problem_creating.builder()
                        .id(id)
                        .problem_id(problem_id)
                        .test_id(test_id)
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return problemCreatings;
    }
}
