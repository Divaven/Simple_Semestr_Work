package ru.itis.repositories.impl;

import ru.itis.models.Topic;
import ru.itis.models.User;
import ru.itis.repositories.CrudRepositories;
import ru.itis.repositories.TopicRepositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicRepositoriesImpl implements TopicRepositories {
    private static final String URL = "jdbc:postgresql://localhost:5432/Valorant";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Arsen2005";

    private static final String INSERT_TOPIC = "INSERT INTO topic (topic, info_id) VALUES (?, ?)";
    private static final String DELETE_TOPIC = "DELETE FROM topic WHERE id = ?";
    private static final String UPDATE_TOPIC = "UPDATE topic SET topic = ?, info_id = ? WHERE id = ?";
    private static final String SELECT_TOPIC = "SELECT * FROM topic";


    @Override
    public void save(Topic topic) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOPIC);

            preparedStatement.setString(1, topic.getTopic());
            preparedStatement.setInt(2, topic.getInfo_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Topic topic) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TOPIC);

            preparedStatement.setInt(1, topic.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Topic topic) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOPIC);

            preparedStatement.setString(1, topic.getTopic());
            preparedStatement.setInt(2, topic.getInfo_id());
            preparedStatement.setInt(3, topic.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Topic> getAll() {
        List<Topic> topics = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOPIC);

             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String topic = resultSet.getString("topic");
                int info_id = resultSet.getInt("info_id");
                topics.add(Topic.builder()
                        .id(id)
                        .topic(topic)
                        .info_id(info_id)
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return topics;
    }
}
