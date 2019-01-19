package cn.nju.edu.service;

import cn.nju.edu.vo.StoryRoleVo;

import java.util.List;

public interface StoryRoleService {
    StoryRoleVo getRoleByRoleNameAndStoryId(String roleName, int storyId);

    //添加角色
    boolean addStoryRole(StoryRoleVo storyRoleVo);

    //删除角色
    boolean deleteStoryRole(StoryRoleVo storyRoleVo);

    //更新角色信息
    boolean updateStoryRole(StoryRoleVo storyRoleVo);

    //获取角色列表
    List<StoryRoleVo> getStoryRoleList(int storyId);
}
