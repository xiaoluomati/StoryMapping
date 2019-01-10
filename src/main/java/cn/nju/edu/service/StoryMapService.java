package cn.nju.edu.service;

import cn.nju.edu.vo.StoryMapVo;
import cn.nju.edu.entity.StoryMap;

import java.util.List;

public interface StoryMapService {

    List<StoryMapVo> getStoryMapList(int userId);

    StoryMapVo getStoryMapByName(String name);

    boolean addStoryMap(StoryMapVo storyMapVo);

    boolean deleteStoryMap(StoryMapVo storyMapVo);


}
