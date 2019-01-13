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
        List<StoryRoleVo> storyRoleVos = storyRoleService.getStoryRoleList("xxx",1);
        assertNotNull(storyRoleVos);
        for (StoryRoleVo tmp : storyRoleVos) {
            if (tmp.getRoleName().equals("awayz"))
                assertArrayEquals(
                        new Object[] {
                                tmp.getRoleId(),
                                tmp.getRoleDetail()
                        },
                        new Object[] {
                                1,
                                "master"
                        }
                );
        }
    }

    @Test
    public void addStoryRole() {
        StoryRoleVo storyRoleVo = new StoryRoleVo();
        storyRoleVo.setStoryName("example");
        storyRoleVo.setRoleName("Denny");
        storyRoleVo.setRoleDetail("It's a storyRole");
        storyRoleVo.setUserId(1);
        storyRoleService.addStoryRole(storyRoleVo);
        List<StoryRoleVo> storyRoleVos = storyRoleService.getStoryRoleList("example",1);
        assertNotNull(storyRoleVos);
        for (StoryRoleVo tmp : storyRoleVos) {
            if (tmp.getRoleName().equals("Denny")) {
                assertEquals(tmp.getRoleDetail(), "It's a storyRole");
            }
        }
    }

    @Test
    public void deleteStoryRole() {
        StoryRoleVo storyRoleVo = new StoryRoleVo();
        storyRoleVo.setRoleName("awayz");
        storyRoleVo.setStoryName("xxx");
        storyRoleVo.setRoleId(1);
        storyRoleVo.setRoleDetail("master");
        storyRoleService.deleteStoryRole(storyRoleVo);
        List<StoryRoleVo> storyRoleVos = storyRoleService.getStoryRoleList("xxx",1);
        boolean flag = false;
        if (storyRoleVos != null) {
            for (StoryRoleVo tmp : storyRoleVos) {
                if (tmp.getRoleName().equals("awayz"))
                    flag = true;
            }
        }
        assertFalse(flag);
    }

    @Test
    public void updateStoryRole() {
        StoryRoleVo storyRoleVo = new StoryRoleVo();
        storyRoleVo.setRoleId(1);
        storyRoleVo.setStoryName("xxx");
        storyRoleVo.setRoleName("denny");
        storyRoleVo.setRoleDetail("hhhhhh");
        storyRoleVo.setUserId(1);
        storyRoleService.updateStoryRole(storyRoleVo);
        List<StoryRoleVo> storyRoleVos = storyRoleService.getStoryRoleList("xxx",1);
        for (StoryRoleVo tmp : storyRoleVos) {
            System.out.println(tmp);
            if (tmp.getRoleId() == 1) {
                assertEquals(tmp.getRoleDetail(), "hhhhhh");
                assertEquals(tmp.getRoleName(), "denny");
            }
        }
    }
}