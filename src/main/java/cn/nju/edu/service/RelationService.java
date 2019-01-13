package cn.nju.edu.service;

import cn.nju.edu.vo.RelationVo;

import java.util.List;

public interface RelationService {
    //显示关系列表
    List<RelationVo> getRelationByCardId(int cardId);

    //添加关系
    void addRelation(RelationVo relationVo);

    //删除关系
    void deleteRelation(RelationVo relationVo);
}
