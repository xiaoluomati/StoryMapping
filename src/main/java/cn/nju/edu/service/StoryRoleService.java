package cn.nju.edu.service;

import cn.nju.edu.vo.StoryRoleVo;

import java.util.List;

public interface StoryRoleService {
    //添加角色
    void addStoryRole(StoryRoleVo storyRoleVo);

    //删除角色
    void deleteStoryRole(StoryRoleVo storyRoleVo);

    //更新角色信息
    void updateStoryRole(StoryRoleVo storyRoleVo);

    //获取角色列表
    List<StoryRoleVo> getStoryRoleList(String storyName);
}
