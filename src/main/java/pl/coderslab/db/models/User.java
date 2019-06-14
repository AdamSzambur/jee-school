package pl.coderslab.db.models;

public class User {
    private int id;
    private String userName;
    private String email;
    private String password;
    private int groupId;

    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User() {
    }

    public User(String userName, String email, String password, int groupId) {
        this.userName = userName;
        this.email = email;
        setPassword(password);
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", groupId='" + groupId + '\'';
    }
}
