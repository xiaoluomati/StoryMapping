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
    public List<RelationVo> getRelationByCardId(int cardId) {
        List<RelationVo> relationVos = new ArrayList<>();
        List<Relation> relations = relationRepository.findByCardId(cardId);
        for (Relation relation: relations) {
            RelationVo relationVo = new RelationVo();
            relationVo.setRelationId(relation.getRelationId());
            relationVo.setRoleId(relation.getRoleId());
            relationVo.setCardId(relation.getCardId());
            relationVos.add(relationVo);
        }
        return relationVos;
    }

    @Override
    public boolean addRelation(RelationVo relationVo) {
        int roleId = relationVo.getRoleId();
        int cardId = relationVo.getCardId();
        if (relationRepository.findByRoleIdAndCardId(roleId, cardId) != null) {
            return false;
        }
        Relation relation = new Relation();
        relation.setRoleId(relationVo.getRoleId());
        relation.setCardId(relationVo.getCardId());
        relationRepository.save(relation);
        return true;
    }

    @Override
    public boolean deleteRelation(RelationVo relationVo) {
        Relation relation = new Relation();
        relation.setRelationId(relationVo.getRelationId());
        relation.setRoleId(relationVo.getRoleId());
        relation.setCardId(relationVo.getCardId());
        relationRepository.delete(relation);
        return true;
    }
}
