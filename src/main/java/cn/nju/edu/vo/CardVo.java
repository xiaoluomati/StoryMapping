package cn.nju.edu.vo;

import cn.nju.edu.enumeration.CardState;
import cn.nju.edu.enumeration.CardType;

public class CardVo {
    private String content;

    private CardState state;

    private int cost;

    private int positionX;

    private int positionY;

    private String storyName;

    private int userId;

    private CardType type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CardState getState() {
        return state;
    }

    public void setState(CardState state) {
        this.state = state;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
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

    public String  getStoryName() {
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

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CardVo{" +
                "content='" + content + '\'' +
                ", state='" + state + '\'' +
                ", cost='" + cost + '\'' +
                ", position_x='" + positionX + '\'' +
                ", position_y='" + positionY + '\'' +
                ", story_map_id='" + storyName + '\'' +
                ", user_id='" + userId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
