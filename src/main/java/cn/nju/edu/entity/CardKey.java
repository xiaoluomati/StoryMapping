package cn.nju.edu.entity;

import java.io.Serializable;

public class CardKey implements Serializable{
    private int positionX;
    private int positionY;
    private int storyId;

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

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    @Override
    public String toString() {
        return "CardKey [position_x=" + positionX + ", position_y=" + positionY
                + ", story_id=" + storyId + "]";
    }
}
