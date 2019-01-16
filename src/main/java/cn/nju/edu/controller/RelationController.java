package cn.nju.edu.controller;

import cn.nju.edu.service.RelationService;
import cn.nju.edu.vo.RelationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relation")
public class RelationController {
    @Autowired
    RelationService relationService;

    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
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

    @RequestMapping(value = "/deleteRelation", method = RequestMethod.POST)
    public ResponseEntity deleteRelation(@RequestBody RelationVo relationVo) {
        if (relationService.deleteRelation(relationVo)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
