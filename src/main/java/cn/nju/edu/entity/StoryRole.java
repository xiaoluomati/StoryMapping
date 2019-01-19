package cn.nju.edu.entity;

import javax.persistence.*;

@Entity
@Table(name = "story_role")
public class StoryRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    private String roleName;
    private String roleDetail;
    private int storyId;

    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(name = "role_detail")
    public String getRoleDetail() {
        return roleDetail;
    }

    public void setRoleDetail(String roleDetail) {
        this.roleDetail = roleDetail;
    }

    @Column(name = "story_id")
    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    @Override
    public String toString() {
        return "StoryRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDetail='" + roleDetail + '\'' +
                ", storyId=" + storyId +
                '}';
    }
}
