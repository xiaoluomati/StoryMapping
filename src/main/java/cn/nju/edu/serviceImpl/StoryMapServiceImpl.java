package cn.nju.edu.serviceImpl;

import cn.nju.edu.entity.Card;
import cn.nju.edu.entity.Collaborator;
import cn.nju.edu.entity.StoryMap;
import cn.nju.edu.repository.CardRepository;
import cn.nju.edu.repository.CollaboratorRepository;
import cn.nju.edu.repository.StoryMapRepository;
import cn.nju.edu.service.StoryMapService;
import cn.nju.edu.util.ExcelHelper;
import cn.nju.edu.vo.CollaboratorVo;
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

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

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
            storyMapVo.setStoryId(temp.getStoryId());
            storyMapVolist.add(storyMapVo);
        }

        List<Collaborator> collaborators = collaboratorRepository.findByUserId(userId);
        for(Collaborator temp : collaborators){
            int storyId = temp.getStoryId();
            StoryMap storyMap = storyMapRepository.findByStoryId(storyId);
            StoryMapVo storyMapVo = new StoryMapVo();
            storyMapVo.setStoryName(storyMap.getStoryName());
            storyMapVo.setStoryDescription(storyMap.getStoryDescription());
            storyMapVo.setRelease(storyMap.getRelease());
            storyMapVo.setUserId(storyMap.getUserId());
            storyMapVo.setStoryId(storyMap.getStoryId());
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
    public boolean addCollaborator(CollaboratorVo collaboratorVo) {
        int userId = collaboratorVo.getUserId();
        int storyId = collaboratorVo.getStoryId();
        if(collaboratorRepository.findByStoryIdAndUserId(storyId,userId) != null){
            return false;
        }
        Collaborator collaborator = new Collaborator();
        collaborator.setStoryId(storyId);
        collaborator.setUserId(userId);
        collaboratorRepository.saveAndFlush(collaborator);
        return true;
    }

    @Override
    @Transactional
    public boolean updateStoryMap(StoryMapVo storyMapVo) {
        storyMapRepository.deleteByStoryId(storyMapVo.getStoryId());

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
        // 如果删除的地图的拥有者和编辑用户不同， 在协作者中删除编辑用户
        int userId = storyMapVo.getUserId();
        int editor = storyMapVo.getEditor();
        System.out.println("userId = " + userId);
        System.out.println("editor = " + editor);
        if (editor != userId) {
            Collaborator byStoryIdAndUserId = collaboratorRepository.findByStoryIdAndUserId(storyMapVo.getStoryId(), editor);
            collaboratorRepository.delete(byStoryIdAndUserId);
            return true;
        }

        // 删除地图及其协作者
        StoryMap storyMap = new StoryMap();
        storyMap.setStoryName(storyMapVo.getStoryName());
//        storyMap.setStoryDescription(storyMapVo.getStoryDescription());
//        storyMap.setRelease(storyMapVo.getRelease());
        storyMap.setUserId(storyMapVo.getUserId());
        storyMapRepository.delete(storyMap);

        collaboratorRepository.deleteByStoryId(storyMapVo.getStoryId());
        return true;
    }

    @Override
    @Transactional
    public boolean exportExcel(int storyId) {
        List<Card> cards = cardRepository.findByStoryId(storyId);

        int map_long = 0;
        int map_wide = 0;

        for(Card temp : cards){
            if(temp.getPositionY() > map_long){
                map_long = temp.getPositionY();
            }if(temp.getPositionX() > map_wide){
                map_wide = temp.getPositionX();
            }
        }

        List<String[]> sheet = new ArrayList<>();
        for(int i = 0;i < map_wide;i++){
            String[] line = new String[map_long];
            sheet.add(line);
        }
        ExcelHelper excelHelper = new ExcelHelper();

        for(Card temp : cards){
            System.out.println(temp.toString());
            sheet.get(temp.getPositionX() - 1)[temp.getPositionY() - 1] = temp.getContent();
            System.out.println(sheet.get(temp.getPositionX() - 1)[temp.getPositionY() - 1]);
        }

        for(String[] temp : sheet){
            for(int i = 0;i < temp.length;i++){
                System.out.print(temp[i] + " ");
            }
        }

        excelHelper.writeExcel(sheet, "src/main/resources/Excel/storyMap.xlsx");

        return true;
    }
}
