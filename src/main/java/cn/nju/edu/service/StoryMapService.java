package cn.nju.edu.service;

import cn.nju.edu.vo.StoryMapVo;

import java.util.List;

public interface StoryMapService {

    List<StoryMapVo> getStoryMapList(int userId);

//    StoryMapVo getStoryMapByName(String name);

    boolean addStoryMap(StoryMapVo storyMapVo);

    boolean updateStoryMap(StoryMapVo storyMapVo);

    boolean deleteStoryMap(StoryMapVo storyMapVo);

    boolean exportExcel(int StoryId);


}
