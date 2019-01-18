package cn.nju.edu.repository;

import cn.nju.edu.entity.StoryMap;
import cn.nju.edu.entity.StoryMapKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StoryMapRepository extends CrudRepository<StoryMap, StoryMapKey> {

    List<StoryMap> findByUserId(int userId);

    StoryMap findByStoryId(int storyId);

    @Transactional
    @Modifying
    @Query("delete from StoryMap where storyId = :storyId")
    void deleteByStoryId(@Param("storyId") int storyId);
}
