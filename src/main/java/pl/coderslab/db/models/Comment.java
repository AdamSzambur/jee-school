package pl.coderslab.db.models;

public class Comment {
    private int id;
    private int solutionId;
    private String userName;
    private String description;
    private String created;


    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Comment() {
    }

    public Comment(int solutionId, String userName, String description, String created) {
        this.solutionId = solutionId;
        this.userName = userName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(int solutionId) {
        this.solutionId = solutionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", solutionId=" + solutionId +
                ", userName='" + userName + '\'' +
                ", description='" + description + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}
