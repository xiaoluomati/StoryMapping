package cn.nju.edu.repository;

import cn.nju.edu.entity.StoryMap;
import cn.nju.edu.entity.StoryMapKey;
import cn.nju.edu.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoryMapRepository extends CrudRepository<StoryMap, StoryMapKey> {
    List<StoryMap> findByUserId(int userId);

    List<StoryMap> findByRelease(int release);
}
