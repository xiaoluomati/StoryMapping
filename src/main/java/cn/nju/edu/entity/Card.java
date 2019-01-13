package cn.nju.edu.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "card")
@IdClass(CardKey.class)
public class Card implements Serializable{
    private static final long serialVersionUID = -850216663528399998L;

    private String content;

    private int state;

    private int cost;

    @Id
    @Column(name = "position_x")
    private int positionX;

    @Id
    @Column(name = "position_y")
    private int positionY;

    @Id
    @Column(name = "story_name")
    private String storyName;

    @Id
    @Column(name = "user_id")
    private int userId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
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
        return "card{" +
                "content='" + content + '\'' +
                ", state='" + state + '\'' +
                ", cost='" + cost + '\'' +
                ", position_x='" + positionX + '\'' +
                ", position_y='" + positionY + '\'' +
                ", story_map_id='" + storyName + '\'' +
                ", user_id='" + userId + '\'' +
                '}';
    }
}
