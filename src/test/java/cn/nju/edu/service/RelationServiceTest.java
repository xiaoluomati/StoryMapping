package cn.nju.edu.service;

import cn.nju.edu.StoryMapApplication;
import cn.nju.edu.vo.RelationVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StoryMapApplication.class)
@Transactional
public class RelationServiceTest {
    @Autowired
    RelationService relationService;

    @Test
    public void getRelationByCard() {
        List<RelationVo> relationVos = relationService.getRelationByCard(1,1,"awayz",1);
        assertNotNull(relationVos);
        for (RelationVo relationVo: relationVos) {
            if (relationVo.getRelationId() == 1) {
                assertEquals(relationVo.getRoleId(),1);
            }
        }
    }

    @Test
    public void addRelation() {
        RelationVo relationVo = new RelationVo();
        relationVo.setRoleId(1);
        relationVo.setPositionX(2);
        relationVo.setPositionY(2);
        relationVo.setStoryName("awayz");
        relationVo.setUserId(1);
        relationService.addRelation(relationVo);
        List<RelationVo> relationVos = relationService.getRelationByCard(2,2,"awayz",1);
        assertNotNull(relationVos);
        for (RelationVo tmp : relationVos) {
            if (tmp.getRoleId() == 1) {
                assertTrue(tmp.getRelationId() > 0);
            }
        }
    }

    @Test
    public void deleteRelation() {
        RelationVo relationVo = new RelationVo();
        relationVo.setRelationId(2);
        relationVo.setRoleId(2);
        relationVo.setPositionX(1);
        relationVo.setPositionY(1);
        relationVo.setStoryName("awayz");
        relationVo.setUserId(1);
        relationService.deleteRelation(relationVo);
        List<RelationVo> relationVos = relationService.getRelationByCard(1,1,"awayz",1);
        boolean isDeleted = true;
        for (RelationVo tmp : relationVos) {
            if (tmp.getRoleId() == 2 && tmp.getRelationId() == 2) {
                isDeleted = false;
            }
        }
        assertTrue(isDeleted);
    }
}