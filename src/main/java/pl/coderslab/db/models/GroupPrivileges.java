package pl.coderslab.db.models;

public class GroupPrivileges {
    int id;
    int groupId;
    int learner;
    int teacher;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getLearner() {
        return learner;
    }

    public void setLearner(int learner) {
        this.learner = learner;
    }

    public int getTeacher() {
        return teacher;
    }

    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }

    public GroupPrivileges() {
    }

    public GroupPrivileges(int groupId, int learner, int teacher) {
        this.groupId = groupId;
        this.learner = learner;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return  ", learner=" + learner +
                ", teacher=" + teacher;
    }
}
