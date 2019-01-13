package cn.nju.edu.repository;

import cn.nju.edu.entity.Relation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationRepository extends CrudRepository<Relation, Integer> {

    List<Relation> findByPositionXAndPositionYAndStoryNameAndUserId(int positionX, int positionY, String storyName, int userId);
}
