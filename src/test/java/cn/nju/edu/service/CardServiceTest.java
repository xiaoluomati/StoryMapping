package cn.nju.edu.service;

import cn.nju.edu.enumeration.CardState;
import cn.nju.edu.enumeration.CardType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import cn.nju.edu.vo.CardVo;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CardServiceTest {

    @Autowired
    CardService cardService;

    @Test
    public void getCardList(){
        List<CardVo> cardVos = cardService.getCardList(1);
        String content = "";
        CardState state = null;
        CardType type = null;
        int storyId = 0;
        int cardId = 0;

        for(CardVo card : cardVos){
            if(card.getPositionX() == 5 && card.getPositionY() == 1){
                content = card.getContent();
                state = card.getState();
                type = card.getType();
                storyId = card.getStoryId();
                cardId = card.getCardId();
            }
        }

        Assert.assertArrayEquals(
                new Object[]{
                        content,
                        state,
                        type,
                        storyId,
                        cardId
                },
                new Object[]{
                        "awayz is a rbq",
                        CardState.DOING,
                        CardType.USER_STORY,
                        1,
                        1
                }
        );
    }

    @Test
//    @Rollback(false)
    public void addCard(){
        CardVo cardVo = new CardVo();
        cardVo.setContent("xiaoluomati");
        cardVo.setState(CardState.DOING);
        cardVo.setCost(55);
        cardVo.setPositionX(7);
        cardVo.setPositionY(3);
        cardVo.setStoryId(1);
        cardVo.setType(CardType.USER_STORY);

        cardService.addCard(cardVo);

        CardVo cardVo1 = new CardVo();
        cardVo1.setContent("xiaoluomati");
        cardVo1.setState(CardState.DOING);
        cardVo1.setCost(55);
        cardVo1.setPositionX(7);
        cardVo1.setPositionY(4);
        cardVo1.setStoryId(1);
        cardVo1.setType(CardType.USER_STORY);

        cardService.addCard(cardVo1);

        List<CardVo> cardVos = cardService.getCardList(1);
        String content = "";
        CardState state = null;
        CardType type = null;
        int storyId = 0;
        int cardId = 2;

        for(CardVo card : cardVos){
            if(card.getPositionX() == 7 && card.getPositionY() == 3){
                content = card.getContent();
                state = card.getState();
                type = card.getType();
                storyId = card.getStoryId();
                cardId = card.getCardId();
            }
            System.out.println("card.getCardId() = " + card.getCardId());
        }

        Assert.assertArrayEquals(
                new Object[]{
                        content,
                        state,
                        type,
                        storyId,
                },
                new Object[]{
                        "xiaoluomati",
                        CardState.DOING,
                        CardType.USER_STORY,
                        1,
                }
        );
    }

    @Test
    public void deleteCard(){
        CardVo cardVo = new CardVo();
        cardVo.setContent("awayz is a rbq");
        cardVo.setState(CardState.DOING);
        cardVo.setCost(38);
        cardVo.setPositionX(5);
        cardVo.setPositionY(1);
        cardVo.setStoryId(1);
        cardVo.setType(CardType.USER_STORY);

        cardService.deleteCard(cardVo);

        List<CardVo> cardVos = cardService.getCardList(1);

        boolean isDeleted = true;
        for(int i = 0;i < cardVos.size();i++){
            CardVo temp = cardVos.get(i);
            if(temp.getPositionX() == 5 && temp.getPositionY() == 1){
                isDeleted = false;
            }
        }
        Assert.assertEquals(isDeleted,true);

    }

}
