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
        List<StoryRoleVo> storyRoleVos = storyRoleService.getStoryRoleList("xxx");
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
        storyRoleVo.setRoleId(2);
        storyRoleVo.setRoleName("Denny");
        storyRoleVo.setRoleDetail("It's a storyRole");
        storyRoleService.addStoryRole(storyRoleVo);
        List<StoryRoleVo> storyRoleVos = storyRoleService.getStoryRoleList("example");
        assertNotNull(storyRoleVos);
        boolean flag = false;
        for (StoryRoleVo tmp : storyRoleVos) {
            if (tmp.getRoleName().equals("Denny")) {
                flag = true;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void deleteStoryRole() {
        StoryRoleVo storyRoleVo = new StoryRoleVo();
        storyRoleVo.setRoleName("awayz");
        storyRoleVo.setStoryName("xxx");
        storyRoleVo.setRoleId(1);
        storyRoleVo.setRoleDetail("master");
        storyRoleService.deleteStoryRole(storyRoleVo);
        List<StoryRoleVo> storyRoleVos = storyRoleService.getStoryRoleList("xxx");
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
        storyRoleVo.setStoryName("xxx");
        storyRoleVo.setRoleName("awayz");
        storyRoleVo.setRoleDetail("hhhhhh");
        storyRoleService.updateStoryRole(storyRoleVo);
        List<StoryRoleVo> storyRoleVos = storyRoleService.getStoryRoleList("xxx");
        for (StoryRoleVo tmp : storyRoleVos) {
            System.out.println(tmp);
            if (tmp.getRoleName().equals("awayz"))
                assertEquals(tmp.getRoleDetail(), "hhhhhh");
        }
    }
}