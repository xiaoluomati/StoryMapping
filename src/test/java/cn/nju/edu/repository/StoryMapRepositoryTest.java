package cn.nju.edu.repository;

import cn.nju.edu.entity.StoryMapKey;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import cn.nju.edu.entity.StoryMap;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StoryMapRepositoryTest {
    @Autowired
    StoryMapRepository storyMapRepository;

    @Test
    public void findById(){
        StoryMapKey id = new StoryMapKey();
        id.setStoryName("xxx");
        id.setUserId(1);
        StoryMap storyMap = storyMapRepository.findById(id).get();
        Assert.assertNotNull(storyMap);
        Assert.assertEquals(storyMap.getStoryDescription(),"xxxxxx");
    }

    @Test
    public void save(){
        StoryMap storyMap = new StoryMap();
        storyMap.setStoryName("GuideMap");
        storyMap.setStoryDescription("sample project");
        storyMap.setRelease(3);
        storyMap.setUserId(1);
        storyMapRepository.save(storyMap);

        StoryMapKey id = new StoryMapKey();
        id.setStoryName("GuideMap");
        id.setUserId(1);
        StoryMap storyMap1 = storyMapRepository.findById(id).get();
        Assert.assertNotNull(storyMap1);
        Assert.assertEquals(storyMap1.getStoryDescription(),"sample project");
    }

    @Test
    public void findByUserId(){
        List<StoryMap> storyMaps = storyMapRepository.findByUserId(1);
        Assert.assertNotNull(storyMaps);
        Assert.assertEquals(storyMaps.get(0).getStoryDescription(),"xxxxxx");
    }

    @Test
    public void update(){
        StoryMapKey id = new StoryMapKey();
        id.setStoryName("xxx");
        id.setUserId(1);
        StoryMap storyMap = storyMapRepository.findById(id).get();
        storyMap.setStoryDescription("xxxxx");
        storyMapRepository.save(storyMap);

        StoryMap storyMap1 = storyMapRepository.findById(id).get();
        Assert.assertNotNull(storyMap1);
        Assert.assertEquals(storyMap1.getStoryDescription(),"xxxxx");
    }

    @Test
    public void delete(){
        StoryMapKey id = new StoryMapKey();
        id.setStoryName("xxx");
        id.setUserId(1);
        StoryMap storyMap = storyMapRepository.findById(id).get();
        storyMapRepository.delete(storyMap);

//        StoryMap storyMap1 = new StoryMap();
//        storyMap1.setStoryName("GuideMap");
//        storyMap1.setStoryDescription("sample project");
//        storyMap1.setRelease(3);
//        storyMap1.setUserId(1);
//        storyMapRepository.save(storyMap1);

        List<StoryMap> storyMaps = storyMapRepository.findByUserId(1);
        Assert.assertNotNull(storyMaps);
        Assert.assertEquals(storyMaps.size(),0);
    }

}
