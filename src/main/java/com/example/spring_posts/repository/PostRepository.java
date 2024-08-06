package com.example.spring_posts.repository;

import com.example.spring_posts.model.Post;
import static com.example.spring_posts.Constants.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    List<Post> postList;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static Connection _connection;

    public PostRepository() { }


    public static void sqlConnection() {
        try {
            _connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            // connection issues
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // handle any other exceptions
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Post> getAllPosts() {
        try {
            postList = new ArrayList<>();
            sqlConnection();
            String sql = "SELECT * FROM posts";
            PreparedStatement statement = _connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String contacts = resultSet.getString("contacts");
                LocalDateTime created_at = LocalDateTime.parse(resultSet.getString("created_at"), formatter);
                //postList.add(new Post(id, title, content, contacts, created_at));
                postList.add(new Post(title, content, contacts));
            }
            return postList;
        } catch (SQLException e) {
            //
        }
        return new ArrayList<>();
    }


    public Post addPost(Post post) {
        try {
            sqlConnection();
            String sql = "INSERT INTO posts ( title, content, contacts, created_at) VALUES (?,?,?,?)";
            PreparedStatement statement = _connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setString(3, post.getContact());
            statement.setString(4, LocalDateTime.now().format(formatter));
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                post.setId(resultSet.getLong(1));
                return post;
            }
        } catch (SQLException e) {
            //
        }
        return null;
    }



}
