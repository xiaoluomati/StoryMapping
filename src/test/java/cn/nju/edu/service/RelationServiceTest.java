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
        RelationVo testCase = new RelationVo();
        testCase.setRoleId(2);
        testCase.setCardId(3);
        relationService.addRelation(testCase);

        List<RelationVo> relationVos = relationService.getRelationByCardId(3);
        assertNotNull(relationVos);
    }

    @Test
    public void addRelation() {
        RelationVo relationVo = new RelationVo();
        relationVo.setRoleId(2);
        relationVo.setCardId(3);
        relationService.addRelation(relationVo);
        List<RelationVo> relationVos = relationService.getRelationByCardId(3);
        assertNotNull(relationVos);
        for (RelationVo tmp : relationVos) {
            if (tmp.getRoleId() == 1) {
                assertTrue(tmp.getRelationId() > 0);
            }
        }
    }

    @Test
    public void deleteRelation() {
        RelationVo testCase = new RelationVo();
        testCase.setRoleId(2);
        testCase.setCardId(3);
        relationService.addRelation(testCase);

        RelationVo relationVo = new RelationVo();
        relationVo.setRelationId(2);
        relationVo.setRoleId(2);
        relationVo.setCardId(1);
        relationService.deleteRelation(relationVo);
        List<RelationVo> relationVos = relationService.getRelationByCardId(1);
        boolean isDeleted = true;
        for (RelationVo tmp : relationVos) {
            if (tmp.getRoleId() == 2 && tmp.getRelationId() == 2) {
                isDeleted = false;
            }
        }
        assertTrue(isDeleted);
    }
}