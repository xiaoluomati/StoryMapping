package cn.nju.edu.entity;

import java.io.Serializable;

public class CardKey implements Serializable{
    private int positionX;
    private int positionY;
    private String storyName;
    private int userId;

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
        return "CardKey [position_x=" + positionX + ", position_y=" + positionY
                + ", story_map_id=" + storyName + ", user_id=" + userId + "]";
    }
}
