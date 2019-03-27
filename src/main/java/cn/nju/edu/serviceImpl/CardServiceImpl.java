package cn.nju.edu.serviceImpl;

import cn.nju.edu.entity.CardKey;
import cn.nju.edu.enumeration.CardState;
import cn.nju.edu.enumeration.CardType;
import cn.nju.edu.repository.CardRepository;
import cn.nju.edu.service.CardService;
import cn.nju.edu.vo.CardVo;
import cn.nju.edu.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;


    @Override
    public List<CardVo> getCardList(int storyId) {
        List<Card> cards = cardRepository.findByStoryId(storyId);
        List<CardVo> cardVos = new ArrayList<>();
        for(int i = 0;i < cards.size();i++){
            Card temp = cards.get(i);
            CardVo cardVo = new CardVo();
            cardVo.setTitle(temp.getTitle());
            cardVo.setContent(temp.getContent());
            cardVo.setState(CardState.values()[temp.getState()]);
            cardVo.setCost(temp.getCost());
            cardVo.setPositionX(temp.getPositionX());
            cardVo.setPositionY(temp.getPositionY());
            cardVo.setStoryId(temp.getStoryId());
            int x= temp.getPositionX();
            cardVo.setType(CardType.values()[x > 2 ? 2 : x]);
            cardVo.setCardId(temp.getCardId());
            cardVos.add(cardVo);
        }
        return cardVos;
    }

    @Override
    public boolean addCard(String source,CardVo cardVo) {
        int x = cardVo.getPositionX();
        int y = cardVo.getPositionY();
        int id = cardVo.getStoryId();
        List<CardVo> cardVos = getCardList(id);
        List<Card> toDelete = new ArrayList<>();
        List<Card> toSave = new ArrayList<>();
        for(CardVo temp : cardVos){
            if(x < 3){//对activity和task卡片来说是右边的卡片右移一格（全部右边的卡片）
                if(source.equals("right")){
                    if(temp.getPositionY() >= y){
                        if(x == 2){
                            CardKey key = new CardKey();
                            key.setPositionX(cardVo.getPositionX());
                            key.setPositionY(cardVo.getPositionY());
                            key.setStoryId(cardVo.getStoryId());
                            boolean flag1 = cardRepository.existsById(key);

                            CardKey key1 = new CardKey();
                            key1.setPositionX(cardVo.getPositionX() - 1);
                            key1.setPositionY(cardVo.getPositionY());
                            key1.setStoryId(cardVo.getStoryId());
                            boolean flag2 = cardRepository.existsById(key1);

                            if(flag1 == false && flag2 == false){
                                break;
                            }
                        }

                        Card card1 = new Card();
                        card1.setTitle(temp.getTitle());
                        card1.setContent(temp.getContent());
                        card1.setState(temp.getState().ordinal());
                        card1.setCost(temp.getCost());
                        card1.setPositionX(temp.getPositionX());
                        card1.setPositionY(temp.getPositionY() + 1);
                        card1.setStoryId(temp.getStoryId());
                        card1.setCardId(temp.getCardId());
                        System.out.println("--------------------- " + card1.toString());
                        toSave.add(card1);

                        Card card3 = new Card();
                        card3.setTitle(temp.getTitle());
                        card3.setContent(temp.getContent());
                        card3.setState(temp.getState().ordinal());
                        card3.setCost(temp.getCost());
                        card3.setPositionX(temp.getPositionX());
                        card3.setPositionY(temp.getPositionY());
                        card3.setStoryId(temp.getStoryId());
                        card3.setCardId(temp.getCardId());
                        toDelete.add(card3);
                    }
                }
            }
            else {//对story卡片来说是下面的卡片下移一格（仅有此列的卡片）
                if(temp.getPositionX() >= x && temp.getPositionY() == y){
                    Card card2 = new Card();
                    card2.setTitle(temp.getTitle());
                    card2.setContent(temp.getContent());
                    card2.setState(temp.getState().ordinal());
                    card2.setCost(temp.getCost());
                    card2.setPositionX(temp.getPositionX() + 1);
                    card2.setPositionY(temp.getPositionY());
                    card2.setStoryId(temp.getStoryId());
                    card2.setCardId(temp.getCardId());
                    System.out.println("--------------------- " + card2.toString());
                    toSave.add(card2);

                    Card card3 = new Card();
                    card3.setTitle(temp.getTitle());
                    card3.setContent(temp.getContent());
                    card3.setState(temp.getState().ordinal());
                    card3.setCost(temp.getCost());
                    card3.setPositionX(temp.getPositionX());
                    card3.setPositionY(temp.getPositionY());
                    card3.setStoryId(temp.getStoryId());
                    card3.setCardId(temp.getCardId());
                    toDelete.add(card3);
                }
            }
        }

        for(Card temp : toDelete){
            System.out.println("delete: " + temp.toString());
        }for(Card temp : toSave){
            System.out.println("save: " + temp.toString());
        }

        for(Card temp : toDelete){
            System.out.println("deleting: " + temp.toString());
            cardRepository.delete(temp);
            cardRepository.flush();
        }for(Card temp : toSave){
            System.out.println("saving: " + temp.toString());
            cardRepository.saveAndFlush(temp);
        }
//        cardRepository.deleteAll(toUpdate);
//        cardRepository.saveAll(toUpdate);

        Card card = new Card();
        card.setTitle(cardVo.getTitle());
        card.setContent(cardVo.getContent());
        card.setState(cardVo.getState().ordinal());
        card.setCost(cardVo.getCost());
        card.setPositionX(x);
        card.setPositionY(y);
        card.setStoryId(id);
        System.out.println("--------------------- " + card.toString());
        cardRepository.saveAndFlush(card);
        return true;
    }

    @Override
    public boolean updateCard(CardVo cardVo) {
//        Card temp = cardRepository.findByCardId(cardVo.getCardId());

//        System.out.println("--------------------- " + temp.toString());

        cardRepository.deleteByCardId(cardVo.getCardId());
        System.out.println("delete!------");

        Card card = new Card();
        card.setTitle(cardVo.getTitle());
        card.setContent(cardVo.getContent());
        card.setState(cardVo.getState().ordinal());
        card.setCost(cardVo.getCost());
        card.setPositionX(cardVo.getPositionX());
        card.setPositionY(cardVo.getPositionY());
        card.setStoryId(cardVo.getStoryId());
        card.setCardId(cardVo.getCardId());
        System.out.println("--------------------- " + card.toString());

        cardRepository.saveAndFlush(card);
        return true;
    }

    @Override
    public boolean updateCardList(List<CardVo> cardVoList) {
        for(CardVo temp : cardVoList){
            Card card = new Card();
            card.setTitle(temp.getTitle());
            card.setContent(temp.getContent());
            card.setState(temp.getState().ordinal());
            card.setCost(temp.getCost());
            card.setPositionX(temp.getPositionX());
            card.setPositionY(temp.getPositionY());
            card.setStoryId(temp.getStoryId());
            card.setCardId(temp.getCardId());
            cardRepository.delete(card);
            cardRepository.flush();
        }

        for(CardVo cardVo : cardVoList){
            Card card = new Card();
            card.setTitle(cardVo.getTitle());
            card.setContent(cardVo.getContent());
            card.setState(cardVo.getState().ordinal());
            card.setCost(cardVo.getCost());
            card.setPositionX(cardVo.getPositionX());
            card.setPositionY(cardVo.getPositionY());
            card.setStoryId(cardVo.getStoryId());
            card.setCardId(cardVo.getCardId());
            cardRepository.saveAndFlush(card);
        }
        return true;
    }

    @Override
    public boolean deleteCard(CardVo cardVo) {
        Card card = new Card();
//        card.setContent(cardVo.getContent());
//        card.setState(cardVo.getState().ordinal());
//        card.setCost(cardVo.getCost());
        int x = cardVo.getPositionX();
        int y = cardVo.getPositionY();
        int id = cardVo.getStoryId();
        card.setPositionX(x);
        card.setPositionY(y);
        card.setStoryId(id);
//        card.setCardId(cardVo.getCardId());
        cardRepository.delete(card);

        int left_move = 0;

        boolean has_father = true;
        for(CardVo temp:getCardList(id)){
            if(temp.getPositionX() < x && temp.getPositionY() == y){
                has_father = false;
            }
        }
        if(has_father == false){
            left_move = 1;
        }

        if(x < 3){//删除所有子卡片
            List<CardVo> cardVos = getCardList(id);
            for(CardVo temp : cardVos){
                boolean will_delete = false;
                if(temp.getPositionX() > x){//子卡片候选集
                    if(temp.getPositionY() == y){//同一列必为子卡片
                        will_delete = true;
                    }
                    else if(temp.getPositionY() > y){//不同列可能为子卡片，对候选集进行筛选
                        for(CardVo temp2 : cardVos){
                            if(temp2.getPositionX() == x && temp2.getPositionY() > y
                            && temp2.getPositionY() <= temp.getPositionY()){//存在上级卡片的不为子卡片
                                will_delete = false;
                                break;
                            }
                            will_delete = true;
                        }
                    }
                }
                if(will_delete == true){
                    System.out.println("bias :" + (temp.getPositionY() - y + 1));
                    if(temp.getPositionY() - y  + 1 > left_move){
                        left_move = temp.getPositionY() - y + 1;
                        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=leftmove: " + left_move);
                    }
                    Card card1 = new Card();
                    card1.setPositionX(temp.getPositionX());
                    card1.setPositionY(temp.getPositionY());
                    card1.setStoryId(temp.getStoryId());
                    cardRepository.delete(card1);
                }
            }
        }

        if(left_move > 0){
            List<CardVo> toDelete = getCardList(id);
            List<CardVo> toAdd = new ArrayList<>();
            for(CardVo temp:toDelete){
                if(temp.getPositionY() > y){
                    Card card1 = new Card();
                    int x1 = temp.getPositionX();
                    int y1 = temp.getPositionY();
                    int id1 = temp.getStoryId();
                    card1.setPositionX(x1);
                    card1.setPositionY(y1);
                    card1.setStoryId(id1);
                    cardRepository.delete(card1);
                    cardRepository.flush();
                    temp.setPositionY(temp.getPositionY() - left_move);
                    System.out.println("left_move: " + left_move);
                    System.out.println("update here! " + temp.toString());
                    toAdd.add(temp);
                }
            }
            for(CardVo temp : toAdd){
                Card card1 = new Card();
                card1.setTitle(temp.getTitle());
                card1.setContent(temp.getContent());
                card1.setState(temp.getState().ordinal());
                card1.setCost(temp.getCost());
                card1.setPositionX(temp.getPositionX());
                card1.setPositionY(temp.getPositionY());
                card1.setStoryId(temp.getStoryId());
                card1.setCardId(temp.getCardId());
                cardRepository.saveAndFlush(card1);
            }
        }


        return true;
    }

}
