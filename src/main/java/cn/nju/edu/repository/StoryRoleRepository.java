package cn.nju.edu.repository;

import cn.nju.edu.entity.StoryRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoryRoleRepository extends CrudRepository<StoryRole, Integer> {
    StoryRole findByStoryNameAndRoleName(String storyName, String roleName);

    List<StoryRole> findByStoryName(String storyName);
}
