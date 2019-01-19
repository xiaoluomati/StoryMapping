package cn.nju.edu.controller;

import cn.nju.edu.service.StoryRoleService;
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

    @RequestMapping(value = "/getStoryRoleList", method = RequestMethod.GET)
    public ResponseEntity<List<StoryRoleVo>> getStoryRoleList(@RequestParam(value = "storyName") String storyName,
                                                              @RequestParam(value = "userId") int userId) {
        return new ResponseEntity<>(storyRoleService.getStoryRoleList(storyName, userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/addStoryRole", method = RequestMethod.POST)
    public ResponseEntity addStoryRole(@RequestBody StoryRoleVo storyRoleVo) {
        if (storyRoleService.addStoryRole(storyRoleVo)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/deleteStoryRole", method = RequestMethod.DELETE)
    public ResponseEntity deleteStoryRole(@RequestBody StoryRoleVo storyRoleVo) {
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
}
