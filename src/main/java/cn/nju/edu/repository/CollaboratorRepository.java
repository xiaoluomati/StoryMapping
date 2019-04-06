package cn.nju.edu.repository;

import cn.nju.edu.entity.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollaboratorRepository extends JpaRepository<Collaborator,Integer> {
    Collaborator findByStoryIdAndUserId(int storyId,int userId);

    List<Collaborator> findByUserId(int userId);

    List<Collaborator> findByStoryId(int storyId);

    void deleteByStoryId(int storyId);
}
