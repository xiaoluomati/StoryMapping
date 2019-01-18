package cn.nju.edu.vo;

public class PartStory {
    int id;
    String title;
    String descriprion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriprion() {
        return descriprion;
    }

    public void setDescriprion(String descriprion) {
        this.descriprion = descriprion;
    }

    @Override
    public String toString() {
        return "PartStory{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + descriprion + '\'' +
                '}';
    }
}
