package cn.nju.edu.entity;

import java.io.Serializable;

public class StoryMapKey implements Serializable {

    private String storyName;

    private int userId;

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
        return "StoryMapKey [story_name=" + storyName + ", user_id=" + userId + "]";
    }
}