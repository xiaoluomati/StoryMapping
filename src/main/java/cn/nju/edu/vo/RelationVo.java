package cn.nju.edu.vo;

public class RelationVo {
    private int relationId;
    private int roleId;
    private int positionX;
    private int positionY;
    private String storyName;
    private int userId;

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getStoryName() {
        return storyName;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RelationVo{" +
                "relationId=" + relationId +
                ", roleId=" + roleId +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", storyName='" + storyName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
