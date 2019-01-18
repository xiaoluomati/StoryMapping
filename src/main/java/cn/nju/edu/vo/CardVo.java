package cn.nju.edu.vo;

import cn.nju.edu.enumeration.CardState;
import cn.nju.edu.enumeration.CardType;

public class CardVo {
    private String title;

    private String content;

    private CardState state;

    private int cost;

    private int positionX;

    private int positionY;

    private int storyId;

    private CardType type;

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

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "CardVo{" +
                "title='" + title + '\'' +
                "content='" + content + '\'' +
                ", state='" + state + '\'' +
                ", cost='" + cost + '\'' +
                ", position_x='" + positionX + '\'' +
                ", position_y='" + positionY + '\'' +
                ", story_id='" + storyId + '\'' +
                ", type='" + type + '\'' +
                ", card_id='" + cardId + '\'' +
                '}';
    }
}
