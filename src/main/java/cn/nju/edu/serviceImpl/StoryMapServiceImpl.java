package cn.nju.edu.serviceImpl;

import cn.nju.edu.entity.StoryMap;
import cn.nju.edu.repository.StoryMapRepository;
import cn.nju.edu.service.StoryMapService;
import cn.nju.edu.vo.StoryMapVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoryMapServiceImpl implements StoryMapService {

    @Autowired
    private StoryMapRepository storyMapRepository;

    @Override
    @Transactional
    public List<StoryMapVo> getStoryMapList(int userId) {
        List<StoryMap> storyMaps = storyMapRepository.findByUserId(userId);
        List<StoryMapVo> storyMapVolist = new ArrayList<StoryMapVo>();
        for(int i = 0;i < storyMaps.size();i++){
            StoryMap temp = storyMaps.get(i);
            StoryMapVo storyMapVo = new StoryMapVo();
            storyMapVo.setStoryName(temp.getStoryName());
            storyMapVo.setStoryDescription(temp.getStoryDescription());
            storyMapVo.setRelease(temp.getRelease());
            storyMapVo.setUserId(temp.getUserId());
            storyMapVolist.add(storyMapVo);
        }
        return storyMapVolist;
    }

//    @Override
//    @Transactional
//    public StoryMapVo getStoryMapByName(String name) {
//        StoryMap storyMap = storyMapRepository.findById(name).get();
//        StoryMapVo storyMapVo = new StoryMapVo();
//        storyMapVo.setStoryName(storyMap.getStoryName());
//        storyMapVo.setStoryDescription(storyMap.getStoryDescription());
//        storyMapVo.setRelease(storyMap.getRelease());
//        storyMapVo.setUserId(storyMap.getUserId());
//        return storyMapVo;
//    }

    @Override
    @Transactional
    public boolean addStoryMap(StoryMapVo storyMapVo) {
        StoryMap storyMap = new StoryMap();
        storyMap.setStoryName(storyMapVo.getStoryName());
        storyMap.setStoryDescription(storyMapVo.getStoryDescription());
        storyMap.setRelease(storyMapVo.getRelease());
        storyMap.setUserId(storyMapVo.getUserId());
        storyMapRepository.save(storyMap);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteStoryMap(StoryMapVo storyMapVo) {
        StoryMap storyMap = new StoryMap();
        storyMap.setStoryName(storyMapVo.getStoryName());
        storyMap.setStoryDescription(storyMapVo.getStoryDescription());
        storyMap.setRelease(storyMapVo.getRelease());
        storyMap.setUserId(storyMapVo.getUserId());
        storyMapRepository.delete(storyMap);
        return true;
    }
}
