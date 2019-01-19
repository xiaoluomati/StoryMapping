package cn.nju.edu.service;

import cn.nju.edu.StoryMapApplication;
import cn.nju.edu.vo.StoryRoleVo;
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
public class StoryRoleServiceTest {
    @Autowired
    StoryRoleService storyRoleService;

    @Test
    public void getStoryRoleList() {
        StoryRoleVo testCase = new StoryRoleVo();
        testCase.setRoleName("Denny");
        testCase.setRoleDetail("It's a storyRole");
        testCase.setStoryId(1);
        storyRoleService.addStoryRole(testCase);

        List<StoryRoleVo> storyRoleVos = storyRoleService.getStoryRoleList(1);
        assertNotNull(storyRoleVos);
        for (StoryRoleVo tmp : storyRoleVos) {
            if (tmp.getRoleName().equals("Denny"))
                assertArrayEquals(
                        new Object[] {
                                tmp.getRoleId() > 0,
                                tmp.getRoleDetail()
                        },
                        new Object[] {
                                true,
                                "It's a storyRole"
                        }
                );
        }
    }

    @Test
    public void addStoryRole() {
        StoryRoleVo storyRoleVo = new StoryRoleVo();
        storyRoleVo.setRoleName("Denny");
        storyRoleVo.setRoleDetail("It's a storyRole");
        storyRoleVo.setStoryId(1);
        storyRoleService.addStoryRole(storyRoleVo);
        List<StoryRoleVo> storyRoleVos = storyRoleService.getStoryRoleList(1);
        assertNotNull(storyRoleVos);
        for (StoryRoleVo tmp : storyRoleVos) {
            if (tmp.getRoleName().equals("Denny")) {
                assertEquals(tmp.getRoleDetail(), "It's a storyRole");
            }
        }
    }

    @Test
    public void deleteStoryRole() {
        StoryRoleVo testCase = new StoryRoleVo();
        testCase.setRoleName("Denny");
        testCase.setRoleDetail("It's a storyRole");
        testCase.setStoryId(1);
        storyRoleService.addStoryRole(testCase);

        StoryRoleVo storyRoleVo = new StoryRoleVo();
        storyRoleVo.setRoleId(storyRoleService.getRoleByRoleNameAndStoryId("Denny", 1).getRoleId());
        storyRoleVo.setStoryId(1);
        storyRoleService.deleteStoryRole(storyRoleVo);
        List<StoryRoleVo> storyRoleVos = storyRoleService.getStoryRoleList(1);
        boolean flag = false;
        if (storyRoleVos != null) {
            for (StoryRoleVo tmp : storyRoleVos) {
                if (tmp.getRoleName().equals("Denny"))
                    flag = true;
            }
        }
        assertFalse(flag);
    }

    @Test
    public void updateStoryRole() {
        StoryRoleVo testCase = new StoryRoleVo();
        testCase.setRoleName("Denny");
        testCase.setRoleDetail("It's a storyRole");
        testCase.setStoryId(1);
        storyRoleService.addStoryRole(testCase);

        StoryRoleVo storyRoleVo = new StoryRoleVo();
        int id = storyRoleService.getRoleByRoleNameAndStoryId("Denny", 1).getRoleId();
        storyRoleVo.setRoleId(id);
        storyRoleVo.setRoleName("denny");
        storyRoleVo.setRoleDetail("hhhhhh");
        storyRoleVo.setStoryId(1);
        storyRoleService.updateStoryRole(storyRoleVo);
        List<StoryRoleVo> storyRoleVos = storyRoleService.getStoryRoleList(1);
        for (StoryRoleVo tmp : storyRoleVos) {
            System.out.println(tmp);
            if (tmp.getRoleId() == id) {
                assertEquals(tmp.getRoleDetail(), "hhhhhh");
                assertEquals(tmp.getRoleName(), "denny");
            }
        }
    }
}