package pl.coderslab.db.dao;
import java.sql.*;

import pl.coderslab.db.DbUtil;
import pl.coderslab.db.models.GroupPrivileges;

public class GroupPrivilegesDao {
    private static final String CREATE_QUERY =
            "INSERT INTO group_privileges(group_id, learner, teacher) VALUES (?,?,?)";
    private static final String READ_QUERY =
            "SELECT * FROM group_privileges where id = ?";
    private static final String UPDATE_QUERY =
            "UPDATE group_privileges SET group_id = ?, learner = ?, teacher = ?  where id = ?";
    private static final String DELETE_QUERY =
            "DELETE FROM group_privileges WHERE id = ?";
    private static final String FIND_BY_GROUP_ID_QUERY =
            "SELECT * FROM group_privileges WHERE group_id = ?";
    private static final String UPDATE_FOR_GROUPID_QUERY =
            "UPDATE group_privileges SET learner = ?, teacher = ?  where group_id = ?";




    public GroupPrivileges create(GroupPrivileges groupPrivileges) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, groupPrivileges.getGroupId());
            statement.setInt(2, groupPrivileges.getLearner());
            statement.setInt(3, groupPrivileges.getTeacher());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                groupPrivileges.setId(resultSet.getInt(1));
            }
            return groupPrivileges;
        } catch (SQLException e) {
            System.err.println("Nie utworzono tablicy dostepu grupy.");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public GroupPrivileges read(int groupPermissionId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_QUERY);
            statement.setInt(1, groupPermissionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                GroupPrivileges groupPrivileges = new GroupPrivileges();
                groupPrivileges.setId(resultSet.getInt("id"));
                groupPrivileges.setGroupId(resultSet.getInt("group_id"));
                groupPrivileges.setTeacher(resultSet.getInt("teacher"));
                groupPrivileges.setLearner(resultSet.getInt("learner"));
                return groupPrivileges;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void update(GroupPrivileges groupPrivileges) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setInt(1, groupPrivileges.getGroupId());
            statement.setInt(2, groupPrivileges.getLearner());
            statement.setInt(3, groupPrivileges.getTeacher());
            statement.setInt(4, groupPrivileges.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Nie zaktualizowano tablicy dostepu grupy.");
            System.err.println(e.getMessage());
        }
    }

    public void delete(int groupPrivilegesId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, groupPrivilegesId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Nie usunięto  zmiennej opisującej dostęp.");
            System.err.println(e.getMessage());
        }
    }

    public GroupPrivileges findByIdGroup(int groupPrivilegesId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_BY_GROUP_ID_QUERY);
            statement.setInt(1,groupPrivilegesId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                GroupPrivileges groupPrivileges = new GroupPrivileges();
                groupPrivileges.setId(resultSet.getInt("id"));
                groupPrivileges.setGroupId(resultSet.getInt("group_id"));
                groupPrivileges.setLearner(resultSet.getInt("learner"));
                groupPrivileges.setTeacher(resultSet.getInt("teacher"));
                return groupPrivileges;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void setUpdateForGroupidQuery(GroupPrivileges groupPrivileges) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_FOR_GROUPID_QUERY);
            statement.setInt(1, groupPrivileges.getLearner());
            statement.setInt(2, groupPrivileges.getTeacher());
            statement.setInt(3, groupPrivileges.getGroupId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Nie zaktualizowano tablicy dostepu grupy.");
            System.err.println(e.getMessage());
        }
    }
}
