package cn.nju.edu.service;

import cn.nju.edu.entity.Card;
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
        String title = "";
        String content = "";
        CardState state = null;
        CardType type = null;
        int storyId = 0;
        int cardId = 0;

        for(CardVo card : cardVos){
            if(card.getPositionX() == 5 && card.getPositionY() == 1){
                title = card.getTitle();
                content = card.getContent();
                state = card.getState();
                type = card.getType();
                storyId = card.getStoryId();
                cardId = card.getCardId();
            }
        }

        Assert.assertArrayEquals(
                new Object[]{
                        title,
                        content,
                        state,
                        type,
                        storyId,
                        cardId
                },
                new Object[]{
                        "awayz",
                        "团长好！",
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

        cardService.addCard("down",cardVo);

        CardVo cardVo1 = new CardVo();
        cardVo1.setContent("xiaoluomati");
        cardVo1.setState(CardState.DOING);
        cardVo1.setCost(55);
        cardVo1.setPositionX(7);
        cardVo1.setPositionY(4);
        cardVo1.setStoryId(1);
        cardVo1.setType(CardType.USER_STORY);

        cardService.addCard("down",cardVo1);

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
    public void addCard1(){
        CardVo cardVo = new CardVo();
        cardVo.setContent("xiaoluomati");
        cardVo.setState(CardState.DOING);
        cardVo.setCost(55);
        cardVo.setPositionX(2);
        cardVo.setPositionY(2);
        cardVo.setStoryId(1);
        cardVo.setType(CardType.USER_STORY);

        cardService.addCard("right",cardVo);

        List<CardVo> cardVos = cardService.getCardList(1);
        String content1 = "";
        String content2 = "";
        String content3 = "";
        String content4 = "";
        String content5 = "";
        String content = "";

        for(CardVo card : cardVos){
            if(card.getPositionX() == 2 && card.getPositionY() == 2){
                content = card.getContent();
            }if(card.getPositionX() == 2 && card.getPositionY() == 3){
                content1 = card.getContent();
            }if(card.getPositionX() == 5 && card.getPositionY() == 3){
                content2 = card.getContent();
            }if(card.getPositionX() == 2 && card.getPositionY() == 4){
                content3 = card.getContent();
            }if(card.getPositionX() == 5 && card.getPositionY() == 4){
                content4 = card.getContent();
            }if(card.getPositionX() == 5 && card.getPositionY() == 1){
                content5 = card.getContent();
            }
            System.out.println("card.getContent() = " + card.getContent());
        }

        System.out.println("content = " + content);
        System.out.println("content1 = " + content1);
        System.out.println("content2 = " + content2);
        System.out.println("content3 = " + content3);
        System.out.println("content4 = " + content4);
        System.out.println("content5 = " + content5);

        Assert.assertArrayEquals(
                new Object[]{
                        content,
                        content1,
                        content2,
                        content3,
                        content4,
                        content5,
                },
                new Object[]{
                        "xiaoluomati",
                        "mmmm",
                        "eee",
                        "ooxxxxoo",
                        "xxooooxx",
                        "团长好！",
                }
        );
    }

    @Test
    public void update(){
        CardVo cardVo = new CardVo();
        cardVo.setContent("awayz is a rbq");
        cardVo.setState(CardState.DOING);
        cardVo.setCost(66);
        cardVo.setPositionX(5);
        cardVo.setPositionY(5);
        cardVo.setStoryId(1);
        cardVo.setType(CardType.USER_STORY);
        cardVo.setCardId(1);

        cardService.updateCard(cardVo);

        List<CardVo> cardVos = cardService.getCardList(1);

        String content = "";
        int cost = 0;

        for(CardVo temp : cardVos){
            if(temp.getPositionX() == 5 && temp.getPositionY() == 5){
                content = temp.getContent();
                cost = temp.getCost();
            }
        }

        Assert.assertArrayEquals(
                new Object[]{
                        content,
                        cost
                },
                new Object[]{
                        "awayz is a rbq",
                        66
                }
        );

    }

    @Test
    public void updateList(){
        List<CardVo> cardVos = cardService.getCardList(1);
        for(CardVo temp : cardVos){
            if(temp.getCardId() == 1){
                temp.setPositionY(2);
            }
            else if(temp.getCardId() == 2){
                temp.setPositionY(1);
            }
        }

        cardService.updateCardList(cardVos);

        List<CardVo> cardVoList = cardService.getCardList(1);
        int Y1 = 0;
        int Y2 = 0;
        for(CardVo temp : cardVoList){
            if(temp.getCardId() == 1){
                Y1 = temp.getPositionY();
            }
            else if(temp.getCardId() == 2){
                Y2 = temp.getPositionY();
            }
        }

        Assert.assertArrayEquals(
                new Object[]{
                        Y1,
                        Y2
                },
                new Object[]{
                        2,
                        1
                }
        );

    }

    @Test
    public void deleteCard(){
        CardVo cardVo = new CardVo();
//        cardVo.setContent("awayz is a rbq");
//        cardVo.setState(CardState.DOING);
//        cardVo.setCost(38);
        cardVo.setPositionX(2);
        cardVo.setPositionY(1);
        cardVo.setStoryId(1);
//        cardVo.setType(CardType.USER_STORY);

        cardService.deleteCard(cardVo);

        List<CardVo> cardVos = cardService.getCardList(1);

        System.out.println("------------------------");

        for(CardVo temp:cardVos){
            System.out.println(temp.toString());
        }

        boolean isDeleted1 = true;
        boolean isDeleted2 = true;
        boolean isDeleted3 = true;
        boolean isDeleted4 = true;
        boolean isDeleted5 = true;
        boolean isDeleted6 = true;
        boolean isDeleted7 = true;
        boolean isDeleted8 = true;
        boolean isDeleted9 = true;
        for(int i = 0;i < cardVos.size();i++){
            CardVo temp = cardVos.get(i);
            if(temp.getPositionX() == 5 && temp.getPositionY() == 1 && temp.getCardId() == 1){
                isDeleted1 = false;
            }if(temp.getPositionX() == 5 && temp.getPositionY() == 2 && temp.getCardId() == 6){
                isDeleted2 = false;
            }if(temp.getPositionX() == 2 && temp.getPositionY() == 1 && temp.getCardId() == 3){
                isDeleted3 = false;
            }if(temp.getPositionX() == 1 && temp.getPositionY() == 1){
                isDeleted4 = false;
            }if(temp.getPositionX() == 2 && temp.getPositionY() == 3){
                isDeleted5 = false;
            }if(temp.getPositionX() == 5 && temp.getPositionY() == 3){
                isDeleted6 = false;
            }if(temp.getPositionX() == 2 && temp.getPositionY() == 2 && temp.getCardId() == 7){
                isDeleted7 = false;
            }if(temp.getPositionX() == 2 && temp.getPositionY() == 1 && temp.getCardId() == 5){
                isDeleted8 = false;
            }if(temp.getPositionX() == 5 && temp.getPositionY() == 1 && temp.getCardId() == 2){
                isDeleted9 = false;
            }
        }
        System.out.println("isDeleted1 = " + isDeleted1);
        System.out.println("isDeleted2 = " + isDeleted2);
        System.out.println("isDeleted3 = " + isDeleted3);
        System.out.println("isDeleted4 = " + isDeleted4);
        System.out.println("isDeleted5 = " + isDeleted5);
        System.out.println("isDeleted6 = " + isDeleted6);
        System.out.println("isDeleted7 = " + isDeleted7);
        System.out.println("isDeleted8 = " + isDeleted8);
        System.out.println("isDeleted9 = " + isDeleted9);
        Assert.assertArrayEquals(
                new Object[]{
                        isDeleted1,
                        isDeleted2,
                        isDeleted3,
                        isDeleted4,
                        isDeleted5,
                        isDeleted6,
                        isDeleted7,
                        isDeleted8,
                        isDeleted9
                },
                new Object[]{
                        true,
                        false,
                        true,
                        false,
                        true,
                        true,
                        false,
                        false,
                        false
                }
        );

    }

    @Test
    public void deleteCard1(){
        CardVo cardVo = new CardVo();
//        cardVo.setContent("awayz is a rbq");
//        cardVo.setState(CardState.DOING);
//        cardVo.setCost(38);
        cardVo.setPositionX(1);
        cardVo.setPositionY(1);
        cardVo.setStoryId(1);
//        cardVo.setType(CardType.USER_STORY);

        cardService.deleteCard(cardVo);

        List<CardVo> cardVos = cardService.getCardList(1);

        boolean isDeleted1 = true;
        boolean isDeleted2 = true;
        boolean isDeleted3 = true;
        boolean isDeleted4 = true;
        boolean isDeleted5 = true;
        boolean isDeleted6 = true;
        boolean isDeleted7 = true;
        for(int i = 0;i < cardVos.size();i++){
            CardVo temp = cardVos.get(i);
            if(temp.getPositionX() == 5 && temp.getPositionY() == 1){
                isDeleted1 = false;
            }if(temp.getPositionX() == 5 && temp.getPositionY() == 2){
                isDeleted2 = false;
            }if(temp.getPositionX() == 2 && temp.getPositionY() == 1){
                isDeleted3 = false;
            }if(temp.getPositionX() == 1 && temp.getPositionY() == 1){
                isDeleted4 = false;
            }if(temp.getPositionX() == 2 && temp.getPositionY() == 3){
                isDeleted5 = false;
            }if(temp.getPositionX() == 5 && temp.getPositionY() == 3){
                isDeleted6 = false;
            }if(temp.getPositionX() == 2 && temp.getPositionY() == 2){
                isDeleted7 = false;
            }
        }
        System.out.println("isDeleted1 = " + isDeleted1);
        System.out.println("isDeleted2 = " + isDeleted2);
        System.out.println("isDeleted3 = " + isDeleted3);
        System.out.println("isDeleted4 = " + isDeleted4);
        System.out.println("isDeleted5 = " + isDeleted5);
        System.out.println("isDeleted6 = " + isDeleted6);
        System.out.println("isDeleted7 = " + isDeleted7);
        Assert.assertArrayEquals(
                new Object[]{
                        isDeleted1,
                        isDeleted2,
                        isDeleted3,
                        isDeleted4,
                        isDeleted5,
                        isDeleted6,
                        isDeleted7
                },
                new Object[]{
                        true,
                        true,
                        true,
                        true,
                        true,
                        true,
                        true
                }
        );

    }

}
