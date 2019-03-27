package cn.nju.edu.service;

import cn.nju.edu.entity.StoryMapKey;
import cn.nju.edu.vo.CollaboratorVo;
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

        String description = "";
        int storyId = 0;
        for(int i = 0;i < storyMapVos.size();i++){
            StoryMapVo temp = storyMapVos.get(i);
            if(temp.getStoryName().equals("awayz")){
                description = temp.getStoryDescription();
                storyId = temp.getStoryId();
            }
        }

        Assert.assertArrayEquals(
                new Object[]{
                        description,
                        storyId
                },
                new Object[]{
                        "团长",
                        1
                }
        );
    }

    @Test
    public void getStoryMapList1(){
        CollaboratorVo collaboratorVo = new CollaboratorVo();
        collaboratorVo.setStoryId(175);
        collaboratorVo.setUserId(1);

        storyMapService.addCollaborator(collaboratorVo);

        List<StoryMapVo> storyMapVos = storyMapService.getStoryMapList(1);
        Assert.assertNotNull(storyMapVos);

        String description = "";
        int storyId = 0;

        for(int i = 0;i < storyMapVos.size();i++){
            StoryMapVo temp = storyMapVos.get(i);
            if(temp.getStoryName().equals("storymap")){
                description = temp.getStoryDescription();
                storyId = temp.getStoryId();
            }
        }

        Assert.assertArrayEquals(
                new Object[]{
                        description,
                        storyId
                },
                new Object[]{
                        "storymap for show",
                        175
                }
        );
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

        String description = "";
        int storyId = 0;

        for(int i = 0;i < storyMapVos.size();i++){
            StoryMapVo temp = storyMapVos.get(i);
            if(temp.getStoryName().equals("GuideMap")){
                description = temp.getStoryDescription();
                storyId = temp.getStoryId();
            }
        }

        Assert.assertArrayEquals(
                new Object[]{
                        description,
                },
                new Object[]{
                        "sample project",
                }
        );
    }

    @Test
    public void updateStoryMap(){
        StoryMapVo storyMapVo = new StoryMapVo();
        storyMapVo.setStoryName("awayz");
        storyMapVo.setStoryDescription("its still a rbq");
        storyMapVo.setRelease(3);
        storyMapVo.setUserId(1);

        storyMapService.updateStoryMap(storyMapVo);

        List<StoryMapVo> storyMapVos = storyMapService.getStoryMapList(1);
        Assert.assertNotNull(storyMapVos);

        String description = "";
        int storyId = 0;

        for(int i = 0;i < storyMapVos.size();i++){
            StoryMapVo temp = storyMapVos.get(i);
            if(temp.getStoryName().equals("awayz")){
                description = temp.getStoryDescription();
                storyId = temp.getStoryId();
            }
        }

        Assert.assertArrayEquals(
                new Object[]{
                        description,
                },
                new Object[]{
                        "its still a rbq",
                }
        );
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

        boolean isDeleted = true;
        for(int i = 0;i < storyMapVos.size();i++){
            StoryMapVo temp = storyMapVos.get(i);
            if(temp.getStoryName().equals("xxx")){
                isDeleted = false;
            }
        }
        Assert.assertEquals(isDeleted,true);
    }

    @Test
    public void exportExcel(){
        storyMapService.exportExcel(1);
    }

}
