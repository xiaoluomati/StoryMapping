package cn.nju.edu.vo;

public class CardVo {
    private String content;

    private int state;

    private int cost;

    private int positionX;

    private int positionY;

    private String storyName;

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
                '}';
    }
}
