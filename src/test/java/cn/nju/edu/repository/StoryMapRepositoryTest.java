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
import java.util.NoSuchElementException;

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
    public void findByStoryId(){
        StoryMap storyMap = storyMapRepository.findByStoryId(1);

        Assert.assertNotNull(storyMap);
        Assert.assertEquals(storyMap.getStoryDescription(),"its a rbq");
    }

    @Test
    public void save(){
        StoryMap storyMap = new StoryMap();
        storyMap.setUserId(1);
        storyMap.setStoryName("GuideMap");
        storyMap.setStoryDescription("sample project");
        storyMap.setRelease(3);
        storyMapRepository.save(storyMap);

        StoryMapKey id = new StoryMapKey();
        id.setStoryName("GuideMap");
        id.setUserId(1);
        StoryMap storyMap1 = storyMapRepository.findById(id).get();
        Assert.assertNotNull(storyMap1);
        Assert.assertEquals(storyMap1.getStoryDescription(),"sample project");

//        List<StoryMap> storyMaps = storyMapRepository.findByUserId(1);
//        Assert.assertNotNull(storyMaps);
//        Assert.assertEquals(storyMaps.size(),3);
    }

    @Test
    public void findByUserId(){
        StoryMap storyMap = new StoryMap();
        storyMap.setUserId(1);
        storyMap.setStoryName("GuideMap");
        storyMap.setStoryDescription("sample project");
        storyMap.setRelease(3);
        storyMapRepository.save(storyMap);

        String description = "";
        List<StoryMap> storyMaps = storyMapRepository.findByUserId(1);
        Assert.assertNotNull(storyMaps);
        for(int i = 0;i < storyMaps.size();i++){
            StoryMap temp = storyMaps.get(i);
            if(temp.getStoryName().equals("GuideMap") && temp.getUserId() == 1){
                description = "sample project";
            }
        }
        Assert.assertNotNull(description);
        Assert.assertEquals(description,"sample project");
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

        boolean isDeleted = false;

        try {
            StoryMap storyMap1 = storyMapRepository.findById(id).get();
        }
        catch (NoSuchElementException e){
            isDeleted = true;
        }

        Assert.assertEquals(isDeleted,true);
    }

}
