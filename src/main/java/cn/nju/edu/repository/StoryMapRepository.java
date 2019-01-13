package cn.nju.edu.repository;

import cn.nju.edu.entity.StoryMap;
import cn.nju.edu.entity.StoryMapKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoryMapRepository extends CrudRepository<StoryMap, StoryMapKey> {

    List<StoryMap> findByUserId(int userId);

    StoryMap findByStoryId(int storyId);
}
