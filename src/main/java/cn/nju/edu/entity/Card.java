package cn.nju.edu.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "card")
@IdClass(CardKey.class)
public class Card implements Serializable{
    private static final long serialVersionUID = -850216663528399998L;

    private String title;

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
    @Column(name = "story_id")
    private int storyId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int cardId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "card{" +
                "title='" + title + '\'' +
                "content='" + content + '\'' +
                ", state='" + state + '\'' +
                ", cost='" + cost + '\'' +
                ", position_x='" + positionX + '\'' +
                ", position_y='" + positionY + '\'' +
                ", story_id='" + storyId + '\'' +
                ", card_id='" + cardId + '\'' +
                '}';
    }
}
