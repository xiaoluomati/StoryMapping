package cn.nju.edu.serviceImpl;

import cn.nju.edu.entity.Relation;
import cn.nju.edu.repository.RelationRepository;
import cn.nju.edu.service.RelationService;
import cn.nju.edu.vo.RelationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RelationServiceImpl implements RelationService {
    @Autowired
    RelationRepository relationRepository;

    @Override
    public List<RelationVo> getRelationByCard(int positionX, int positionY, String storyName, int userId) {
        List<RelationVo> relationVos = new ArrayList<>();
        List<Relation> relations = relationRepository.findByPositionXAndPositionYAndStoryNameAndUserId(positionX, positionY, storyName, userId);
        for (Relation relation: relations) {
            RelationVo relationVo = new RelationVo();
            relationVo.setRelationId(relation.getRelationId());
            relationVo.setRoleId(relation.getRoleId());
            relationVo.setPositionX(relation.getPositionX());
            relationVo.setPositionY(relation.getPositionY());
            relationVo.setStoryName(relation.getStoryName());
            relationVo.setUserId(relation.getUserId());
            relationVos.add(relationVo);
        }
        return relationVos;
    }

    @Override
    public void addRelation(RelationVo relationVo) {
        Relation relation = new Relation();
        relation.setRoleId(relationVo.getRoleId());
        relation.setPositionX(relationVo.getPositionX());
        relation.setPositionY(relationVo.getPositionY());
        relation.setStoryName(relationVo.getStoryName());
        relation.setUserId(relationVo.getUserId());
        relationRepository.save(relation);
    }

    @Override
    public void deleteRelation(RelationVo relationVo) {
        Relation relation = new Relation();
        relation.setRelationId(relationVo.getRelationId());
        relation.setRoleId(relationVo.getRoleId());
        relation.setPositionX(relationVo.getPositionX());
        relation.setPositionY(relationVo.getPositionY());
        relation.setStoryName(relationVo.getStoryName());
        relation.setUserId(relationVo.getUserId());
        relationRepository.delete(relation);
    }
}
