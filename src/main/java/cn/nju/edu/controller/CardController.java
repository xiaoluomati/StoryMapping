package cn.nju.edu.controller;

import cn.nju.edu.service.CardService;
import cn.nju.edu.vo.AllCard;
import cn.nju.edu.vo.CardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping("/getCardList")
    public AllCard getCardList(@RequestParam(value="storyId", defaultValue="1") int storyId) {
        List<CardVo> cardVos = cardService.getCardList(storyId);
        int mapLong = 0;
        int mapWide = 0;

        for(CardVo cardVo : cardVos){
            if(cardVo.getPositionY() > mapLong){
                mapLong = cardVo.getPositionY();
            }
            if(cardVo.getPositionX() > mapWide){
                mapWide = cardVo.getPositionX();
            }
        }

        return new AllCard(mapLong,mapWide,cardVos);
    }

    @PostMapping("/addCard")
    public void addCard(@RequestBody CardVo cardVo){
        cardService.addCard(cardVo);
    }

    @PostMapping("/updateCard")
    public void updateCard(@RequestBody CardVo cardVo){
        cardService.updateCard(cardVo);
    }

    @PostMapping("/deleteCard")
    public void deleteCard(@RequestBody CardVo cardVo){
        cardService.deleteCard(cardVo);
    }

}
