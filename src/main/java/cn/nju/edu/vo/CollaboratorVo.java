package cn.nju.edu.vo;

public class CollaboratorVo {
    private int id;
    private int userId;
    private int storyId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    @Override
    public String toString() {
        return "Collaborator{" +
                "id=" + id +
                ", userId=" + userId +
                ", storyId=" + storyId +
                '}';
    }
}
