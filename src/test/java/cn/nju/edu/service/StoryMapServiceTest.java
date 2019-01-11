package cn.nju.edu.service;

import cn.nju.edu.entity.StoryMapKey;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import cn.nju.edu.vo.StoryMapVo;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StoryMapServiceTest {
    @Autowired
    StoryMapService storyMapService;

    @Test
    public void getStoryMapList(){
        List<StoryMapVo> storyMapVos = storyMapService.getStoryMapList(1);
        Assert.assertNotNull(storyMapVos);
        Assert.assertEquals(storyMapVos.get(0).getStoryDescription(),"its a rbq");
    }

    @Test
    public void addStoryMap(){
        StoryMapVo storyMapVo = new StoryMapVo();
        storyMapVo.setStoryName("GuideMap");
        storyMapVo.setStoryDescription("sample project");
        storyMapVo.setRelease(3);
        storyMapVo.setUserId(1);

        storyMapService.addStoryMap(storyMapVo);

        List<StoryMapVo> storyMapVos = storyMapService.getStoryMapList(1);
        Assert.assertNotNull(storyMapVos);
        Assert.assertEquals(storyMapVos.get(1).getStoryDescription(),"sample project");
    }

    @Test
    public void deleteStoryMap(){
        StoryMapVo storyMapVo = new StoryMapVo();
        storyMapVo.setStoryName("xxx");
        storyMapVo.setStoryDescription("xxxxxx");
        storyMapVo.setRelease(3);
        storyMapVo.setUserId(1);

        storyMapService.deleteStoryMap(storyMapVo);

        List<StoryMapVo> storyMapVos = storyMapService.getStoryMapList(1);
        Assert.assertNotNull(storyMapVos);
        Assert.assertEquals(storyMapVos.size(),1);
    }

}
