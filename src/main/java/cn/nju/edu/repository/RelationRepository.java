package cn.nju.edu.repository;

import cn.nju.edu.entity.Relation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationRepository extends CrudRepository<Relation, Integer> {

    Relation findByRoleIdAndCardId(int roleId, int cardId);

    List<Relation> findByCardId(int cardId);
}
