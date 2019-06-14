package pl.coderslab.db.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.coderslab.db.DbUtil;
import pl.coderslab.db.models.Exercise;

public class ExerciseDao {
    private static final String CREATE_QUERY =
            "INSERT INTO exercise(title, description) VALUES (?,?)";
    private static final String READ_QUERY =
            "SELECT * FROM exercise where id = ?";
    private static final String UPDATE_QUERY =
            "UPDATE exercise SET title = ?, description = ?  where id = ?";
    private static final String DELETE_QUERY =
            "DELETE FROM exercise WHERE id = ?";
    private static final String FIND_ALL_QUERY =
            "SELECT * FROM exercise";
    private static final String FIND_ALL_NOT_SOLVED_BY_USER_ID_QUERY =
            "SELECT * FROM exercise where id NOT IN (SELECT exercise_id as user_exercise FROM solution WHERE users_id=?)";

    public Exercise create(Exercise exercise) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                exercise.setId(resultSet.getInt(1));
            }
            return exercise;
        } catch (SQLException e) {
            System.err.println("Nie utworzono zadania.");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Exercise read(int exerciseId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_QUERY);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                return exercise;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    public void update(Exercise exercise) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.setInt(3, exercise.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Nie zaktualizowano danych zadania.");
            System.err.println(e.getMessage());
        }
    }


    public void delete(int exerciseId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, exerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Nie usunięto zadania.");
            System.err.println(e.getMessage());
        }
    }

    public List<Exercise> findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            List<Exercise> exercises = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                exercises.add(exercise);
            }
            return exercises;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<Exercise> findAllNotSolvedByUserId(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            List<Exercise> exercises = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_NOT_SOLVED_BY_USER_ID_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                exercises.add(exercise);
            }
            return exercises;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }


}
