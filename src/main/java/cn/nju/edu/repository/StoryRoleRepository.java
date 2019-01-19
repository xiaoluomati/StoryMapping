package cn.nju.edu.repository;

import cn.nju.edu.entity.StoryRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRoleRepository extends CrudRepository<StoryRole, Integer> {
    StoryRole findByRoleNameAndStoryId(String roleName, int storyId);

    List<StoryRole> findByStoryId(int storyId);
}
