package cn.nju.edu.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "story_map")
public class StoryMap implements Serializable {
    private static final long serialVersionUID = -850216663528399998L;

    private String storyName;
    private String storyDescription;
    private int release;
    private int userId;

    @Id
    @Column(name = "story_name")
    public String getStoryName() {
        return storyName;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

    @Column(name = "story_description")
    public String getStoryDescription() {
        return storyDescription;
    }

    public void setStoryDescription(String storyDescription) {
        this.storyDescription = storyDescription;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "story_map{" +
                "story_name='" + storyName + '\'' +
                ", story_description='" + storyDescription + '\'' +
                ", release='" + release + '\'' +
                ", user_id='" + userId + '\'' +
                '}';
    }
}
