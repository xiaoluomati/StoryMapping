package cn.nju.edu.serviceImpl;

import cn.nju.edu.entity.StoryRole;
import cn.nju.edu.repository.StoryRoleRepository;
import cn.nju.edu.service.StoryRoleService;
import cn.nju.edu.vo.StoryRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoryRoleServiceImpl implements StoryRoleService {
    @Autowired
    StoryRoleRepository storyRoleRepository;

    @Override
    @Transactional
    public List<StoryRoleVo> getStoryRoleList(String storyName, int userId) {
        List<StoryRoleVo> storyRoleVos = new ArrayList<>();
        List<StoryRole> storyRoles = storyRoleRepository.findByStoryNameAndUserId(storyName, userId);
        for (StoryRole storyRole : storyRoles) {
            StoryRoleVo storyRoleVo = new StoryRoleVo();
            storyRoleVo.setRoleId(storyRole.getRoleId());
            storyRoleVo.setRoleName(storyRole.getRoleName());
            storyRoleVo.setRoleDetail(storyRole.getRoleDetail());
            storyRoleVo.setStoryName(storyRole.getStoryName());
            storyRoleVo.setUserId(storyRole.getUserId());
            storyRoleVos.add(storyRoleVo);
        }
        return storyRoleVos;
    }

    @Override
    @Transactional
    public boolean addStoryRole(StoryRoleVo storyRoleVo) {
        StoryRole storyRole = new StoryRole();
        storyRole.setRoleName(storyRoleVo.getRoleName());
        storyRole.setRoleDetail(storyRoleVo.getRoleDetail());
        storyRole.setStoryName(storyRoleVo.getStoryName());
        storyRole.setUserId(storyRoleVo.getUserId());
        storyRoleRepository.save(storyRole);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteStoryRole(StoryRoleVo storyRoleVo) {
        StoryRole storyRole = new StoryRole();
        storyRole.setRoleId(storyRoleVo.getRoleId());
        storyRole.setRoleName(storyRoleVo.getRoleName());
        storyRole.setRoleDetail(storyRoleVo.getRoleDetail());
        storyRole.setStoryName(storyRoleVo.getStoryName());
        storyRole.setUserId(storyRoleVo.getUserId());
        storyRoleRepository.delete(storyRole);
        return true;
    }

    @Override
    @Transactional
    public boolean updateStoryRole(StoryRoleVo storyRoleVo) {
        StoryRole storyRole = new StoryRole();
        storyRole.setRoleId(storyRoleVo.getRoleId());
        storyRole.setRoleName(storyRoleVo.getRoleName());
        storyRole.setRoleDetail(storyRoleVo.getRoleDetail());
        storyRole.setStoryName(storyRoleVo.getStoryName());
        storyRole.setUserId(storyRoleVo.getUserId());
        storyRoleRepository.save(storyRole);
        return true;
    }
}
