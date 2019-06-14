package pl.coderslab.db.dao;

import pl.coderslab.db.DbUtil;
import pl.coderslab.db.models.Solution;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SolutionDao {
    private static final String CREATE_QUERY =
            "INSERT INTO solution(created, updated, description, exercise_id, users_id) VALUES (?,?,?,?,?)";
    private static final String READ_QUERY =
            "SELECT solution.*, users.username as username, exercise.title as title FROM solution JOIN users ON users.id = solution.users_id JOIN exercise ON exercise.id=solution.exercise_id WHERE solution.id=?";
    private static final String UPDATE_QUERY =
            "UPDATE solution SET created=?, updated=?,description=?,exercise_id=?,users_id=?, rating=? where id = ?";
    private static final String DELETE_QUERY =
            "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_QUERY =
            "SELECT solution.*, users.username as username, exercise.title as title FROM solution JOIN users ON users.id = solution.users_id JOIN exercise ON exercise.id=solution.exercise_id ORDER BY created desc";
    private static final String FIND_ALL_QUERY_LIMIT =
            "SELECT solution.*, users.username as username, exercise.title as title FROM solution JOIN users ON users.id = solution.users_id JOIN exercise ON exercise.id=solution.exercise_id ORDER BY created desc LIMIT ?";
    private static final String FIND_ALL_BY_USER_ID_QUERY =
            "SELECT solution.*, users.username as username, exercise.title as title FROM solution JOIN users ON users.id = solution.users_id JOIN exercise ON exercise.id=solution.exercise_id WHERE solution.users_id=? ORDER BY solution.created desc";
    private static final String FIND_ALL_BY_EXERCISE_ID_QUERY =
            "SELECT solution.*, users.username as username, exercise.title as title FROM solution JOIN users ON users.id = solution.users_id JOIN exercise ON exercise.id=solution.exercise_id WHERE solution.exercise_id=? ORDER BY created desc";

    public Solution create(Solution solution) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, solution.getCreated());
            statement.setString(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getExercise_id());
            statement.setInt(5, solution.getUsers_id());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Solution read(int solutionId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createSolution(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Nie odczytano rozwiazania.");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void update(Solution solution) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setString(1, solution.getCreated());
            statement.setString(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getExercise_id());
            statement.setInt(5, solution.getUsers_id());
            statement.setInt(6, solution.getRating());
            statement.setInt(7, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Nie zaktualizowano rozwiązania.");
            System.err.println(e.getMessage());
        }
    }

    public void delete(int solutionId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Nie usunięto rozwiązania.");
            System.err.println(e.getMessage());
        }
    }

    public List<Solution> findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            return createSolutions(resultSet);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<Solution> findAllByExerciseId(int exerciseId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_EXERCISE_ID_QUERY);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            return createSolutions(resultSet);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<Solution> findAllByUserId(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_USER_ID_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            return createSolutions(resultSet);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<Solution> findRecent(int max) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_QUERY_LIMIT);
            statement.setInt(1, max);
            ResultSet resultSet = statement.executeQuery();
            return createSolutions(resultSet);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private List<Solution> createSolutions(ResultSet resultSet) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        while (resultSet.next()) {
            Solution solution = createSolution(resultSet);
            solutions.add(solution);
        }
        return solutions;
    }


    private Solution createSolution(ResultSet resultSet) throws SQLException {
        Solution solution = new Solution();
        solution.setId(resultSet.getInt("id"));
        solution.setCreated(resultSet.getString("created"));
        solution.setUpdated(resultSet.getString("updated"));
        solution.setDescription(resultSet.getString("description"));
        solution.setExercise_id(resultSet.getInt("exercise_id"));
        solution.setUsers_id(resultSet.getInt("users_id"));
        solution.setRating(resultSet.getInt("rating"));
        solution.setUserName(resultSet.getString("username"));
        solution.setExerciseTitle(resultSet.getString("title"));
        return solution;
    }
}
