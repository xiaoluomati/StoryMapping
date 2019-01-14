package cn.nju.edu.controller;

import cn.nju.edu.service.StoryMapService;
import cn.nju.edu.vo.StoryMapVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StoryMapController {


    @Autowired
    StoryMapService storyMapService;

    @GetMapping("/getStoryMapList")
    public List<StoryMapVo> getStoryMapList(@RequestParam(value="userId", defaultValue="1") int userId) {
        return storyMapService.getStoryMapList(userId);
    }

    @PostMapping("/addStoryMap")
    public void addStoryMap(StoryMapVo storyMapVo){
        storyMapService.addStoryMap(storyMapVo);
    }

    @PostMapping("/deleteStoryMap")
    public void deleteStoryMap(StoryMapVo storyMapVo){
        storyMapService.deleteStoryMap(storyMapVo);
    }
}
