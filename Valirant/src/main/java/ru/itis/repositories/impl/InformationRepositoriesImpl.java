package ru.itis.repositories.impl;

import ru.itis.models.Information;
import ru.itis.models.User;
import ru.itis.repositories.InformationRepositories;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InformationRepositoriesImpl implements InformationRepositories {
    private static final String URL = "jdbc:postgresql://localhost:5432/Valorant";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Arsen2005";

    private static final String INSERT_INFORMATION = "INSERT INTO information (text, date, time_to_read) VALUES (?, ?, ?)";
    private static final String DELETE_INFORMATION = "DELETE FROM information WHERE id = ?";
    private static final String UPDATE_INFORMATION = "UPDATE information SET name = ?, password = ?, email = ? WHERE id = ?";
    private static final String SELECT_INFORMATION = "SELECT * FROM information";


    @Override
    public void save(Information information) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INFORMATION);

            preparedStatement.setString(1, information.getText());
            preparedStatement.setObject(2, information.getDate());
            preparedStatement.setInt(3, information.getTime_to_read());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Information information) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INFORMATION);

            preparedStatement.setInt(1, information.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Information information) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INFORMATION);

            preparedStatement.setString(1, information.getText());
            preparedStatement.setObject(2, information.getDate());
            preparedStatement.setInt(3, information.getTime_to_read());
            preparedStatement.setInt(4, information.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Information> getAll() {
        List<Information> information = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INFORMATION);

             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String text = resultSet.getString("text");
                LocalDate date = resultSet.getObject("date", LocalDate.class);
                int time_to_read = resultSet.getInt("time_to_read");
                information.add(Information.builder()
                        .id(id)
                        .text(text)
                        .date(date)
                        .time_to_read(time_to_read)
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return information;
    }
}
