package cn.nju.edu.controller;

import cn.nju.edu.service.RelationService;
import cn.nju.edu.service.StoryRoleService;
import cn.nju.edu.vo.RelationVo;
import cn.nju.edu.vo.StoryRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storyRoles")
public class StoryRoleController {
    @Autowired
    StoryRoleService storyRoleService;

    @Autowired
    RelationService relationService;

    @RequestMapping(value = "/getStoryRoleList", method = RequestMethod.GET)
    public ResponseEntity<List<StoryRoleVo>> getStoryRoleList(@RequestParam(value = "storyId") int storyId) {
        return new ResponseEntity<>(storyRoleService.getStoryRoleList(storyId), HttpStatus.OK);
    }

    @RequestMapping(value = "/addStoryRole", method = RequestMethod.POST)
    public ResponseEntity addStoryRole(@RequestBody StoryRoleVo storyRoleVo) {
        if (storyRoleService.addStoryRole(storyRoleVo)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/deleteStoryRole", method = RequestMethod.DELETE)
    public ResponseEntity deleteStoryRole(StoryRoleVo storyRoleVo) {
        if (storyRoleService.deleteStoryRole(storyRoleVo)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/updateStoryRole", method = RequestMethod.PUT)
    public ResponseEntity updateStoryRole(@RequestBody StoryRoleVo storyRoleVo) {
        if (storyRoleService.updateStoryRole(storyRoleVo)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getRelationList", method = RequestMethod.GET)
    public ResponseEntity<List<RelationVo>> getRelationList(@RequestParam(value = "cardId") int  cardId) {
        return new ResponseEntity<>(relationService.getRelationByCardId(cardId), HttpStatus.OK);
    }

    @RequestMapping(value = "/addRelation", method = RequestMethod.POST)
    public ResponseEntity addRelation(@RequestBody RelationVo relationVo) {
        if (relationService.addRelation(relationVo)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/deleteRelation", method = RequestMethod.DELETE)
    public ResponseEntity deleteRelation(RelationVo relationVo) {
        if (relationService.deleteRelation(relationVo)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
