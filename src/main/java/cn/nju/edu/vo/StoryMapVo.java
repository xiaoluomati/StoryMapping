package cn.nju.edu.vo;

public class StoryMapVo {
    private String storyName;

    private String storyDescription;

    private int release;

    private int userId;

    private int storyId;

    private int editor;

    public int getEditor() {
        return editor;
    }

    public void setEditor(int editor) {
        this.editor = editor;
    }

    public String getStoryName() {
        return storyName;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    @Override
    public String toString() {
        return "StoryMapVo{" +
                "storyName='" + storyName + '\'' +
                ", storyDescription='" + storyDescription + '\'' +
                ", release='" + release + '\'' +
                ", userId='" + userId + '\'' +
                ", storyId='" + storyId + '\'' +
                '}';
    }
}
