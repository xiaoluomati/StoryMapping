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
    public List<StoryRoleVo> getStoryRoleList(int storyId) {
        List<StoryRoleVo> storyRoleVos = new ArrayList<>();
        List<StoryRole> storyRoles = storyRoleRepository.findByStoryId(storyId);
        for (StoryRole storyRole : storyRoles) {
            StoryRoleVo storyRoleVo = new StoryRoleVo();
            storyRoleVo.setRoleId(storyRole.getRoleId());
            storyRoleVo.setRoleName(storyRole.getRoleName());
            storyRoleVo.setRoleDetail(storyRole.getRoleDetail());
            storyRoleVo.setStoryId(storyRole.getStoryId());
            storyRoleVos.add(storyRoleVo);
        }
        return storyRoleVos;
    }

    @Override
    @Transactional
    public StoryRoleVo getRoleByRoleNameAndStoryId(String roleName, int storyId) {
        StoryRole storyRole = storyRoleRepository.findByRoleNameAndStoryId(roleName, storyId);
        StoryRoleVo storyRoleVo = new StoryRoleVo();
        storyRoleVo.setRoleId(storyRole.getRoleId());
        storyRoleVo.setRoleName(storyRole.getRoleName());
        storyRoleVo.setRoleDetail(storyRole.getRoleDetail());
        storyRoleVo.setStoryId(storyRole.getStoryId());
        return storyRoleVo;
    }

    @Override
    @Transactional
    public boolean addStoryRole(StoryRoleVo storyRoleVo) {
        StoryRole storyRole = new StoryRole();
        storyRole.setRoleName(storyRoleVo.getRoleName());
        storyRole.setRoleDetail(storyRoleVo.getRoleDetail());
        storyRole.setStoryId(storyRoleVo.getStoryId());
        storyRoleRepository.save(storyRole);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteStoryRole(StoryRoleVo storyRoleVo) {
        StoryRole storyRole = new StoryRole();
        storyRole.setRoleId(storyRoleVo.getRoleId());
//        storyRole.setRoleName(storyRoleVo.getRoleName());
//        storyRole.setRoleDetail(storyRoleVo.getRoleDetail());
        storyRole.setStoryId(storyRoleVo.getStoryId());
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
        storyRole.setStoryId(storyRoleVo.getStoryId());
        storyRoleRepository.save(storyRole);
        return true;
    }
}
