package pl.coderslab.db.dao;

import pl.coderslab.db.DbUtil;
import pl.coderslab.db.models.Comment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    private static final String CREATE_QUERY =
            "INSERT INTO comment(solution_id, user_name, description, created) VALUES (?,?,?,?)";
    private static final String READ_QUERY =
            "SELECT * FROM comment where id = ?";
    private static final String UPDATE_QUERY =
            "UPDATE comment SET solution_id = ?, user_name = ?, description = ?, created = ?  where id = ?";
    private static final String DELETE_QUERY =
            "DELETE FROM comment WHERE id = ?";
    private static final String FIND_BY_SOLUTION_ID_QUERY =
            "SELECT * FROM comment WHERE solution_id = ? ORDER BY created desc";


    public Comment create(Comment comment) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, comment.getSolutionId());
            statement.setString(2, comment.getUserName());
            statement.setString(3, comment.getDescription());
            statement.setString(4, comment.getCreated());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }
            return comment;
        } catch (SQLException e) {
            System.err.println("Nie utworzono komentarza.");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Comment read(int commentId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_QUERY);
            statement.setInt(1, commentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setSolutionId(resultSet.getInt("solution_id"));
                comment.setUserName(resultSet.getString("user_name"));
                comment.setDescription(resultSet.getString("description"));
                comment.setCreated(resultSet.getString("created"));
                return comment;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void update(Comment comment) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setInt(1, comment.getSolutionId());
            statement.setString(2, comment.getUserName());
            statement.setString(3, comment.getDescription());
            statement.setString(4, comment.getCreated());
            statement.setInt(5, comment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Nie zaktualizowano komentarza.");
            System.err.println(e.getMessage());
        }
    }

    public void delete(int commentId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, commentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Nie usunięto komentarza.");
            System.err.println(e.getMessage());
        }
    }

    public List<Comment> findByIdSolution(int solutionId) {
        try (Connection conn = DbUtil.getConnection()) {
            List<Comment> comments = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_BY_SOLUTION_ID_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setSolutionId(resultSet.getInt("solution_id"));
                comment.setUserName(resultSet.getString("user_name"));
                comment.setDescription(resultSet.getString("description"));
                comment.setCreated(resultSet.getString("created"));
                comments.add(comment);
            }
            return comments;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
