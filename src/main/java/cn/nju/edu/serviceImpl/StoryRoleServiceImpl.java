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
    public List<StoryRoleVo> getStoryRoleList(String storyName) {
        List<StoryRoleVo> storyRoleVos = new ArrayList<>();
        List<StoryRole> storyRoles = storyRoleRepository.findByStoryName(storyName);
        for (StoryRole storyRole : storyRoles) {
            StoryRoleVo storyRoleVo = new StoryRoleVo();
            storyRoleVo.setRoleId(storyRole.getRoleId());
            storyRoleVo.setRoleName(storyRole.getRoleName());
            storyRoleVo.setRoleDetail(storyRole.getRoleDetail());
            storyRoleVo.setStoryName(storyRole.getStoryName());
            storyRoleVos.add(storyRoleVo);
        }
        return storyRoleVos;
    }

    @Override
    @Transactional
    public void addStoryRole(StoryRoleVo storyRoleVo) {
        StoryRole storyRole = new StoryRole();
        storyRole.setRoleId(storyRoleVo.getRoleId());
        storyRole.setRoleName(storyRoleVo.getRoleName());
        storyRole.setRoleDetail(storyRoleVo.getRoleDetail());
        storyRole.setStoryName(storyRoleVo.getStoryName());
        storyRoleRepository.save(storyRole);
    }

    @Override
    @Transactional
    public void deleteStoryRole(StoryRoleVo storyRoleVo) {
        StoryRole storyRole = new StoryRole();
        storyRole.setRoleId(storyRoleVo.getRoleId());
        storyRole.setRoleName(storyRoleVo.getRoleName());
        storyRole.setRoleDetail(storyRoleVo.getRoleDetail());
        storyRole.setStoryName(storyRoleVo.getStoryName());
        storyRoleRepository.delete(storyRole);
    }

    @Override
    @Transactional
    public void updateStoryRole(StoryRoleVo storyRoleVo) {
        StoryRole storyRole = new StoryRole();
        String storyName = storyRoleVo.getStoryName();
        String roleName = storyRoleVo.getRoleName();
        int roleId = storyRoleRepository.findByStoryNameAndRoleName(storyName, roleName).getRoleId();
        storyRole.setRoleId(roleId);
        storyRole.setStoryName(storyName);
        storyRole.setRoleName(roleName);
        storyRole.setRoleDetail(storyRoleVo.getRoleDetail());
        storyRoleRepository.save(storyRole);
    }
}
