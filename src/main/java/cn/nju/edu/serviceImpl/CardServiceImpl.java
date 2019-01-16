package cn.nju.edu.serviceImpl;

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
    public boolean addCard(CardVo cardVo) {
        Card card = new Card();
        card.setContent(cardVo.getContent());
        card.setState(cardVo.getState().ordinal());
        card.setCost(cardVo.getCost());
        card.setPositionX(cardVo.getPositionX());
        card.setPositionY(cardVo.getPositionY());
        card.setStoryId(cardVo.getStoryId());
        cardRepository.save(card);
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

        if(x < 3){//删除所有子卡片
            List<CardVo> cardVos = getCardList(id);
            for(CardVo temp : cardVos){
                boolean will_delete = false;
                if(temp.getPositionX() > x){
                    if(temp.getPositionY() == y){
                        will_delete = true;
                    }
                    else if(temp.getPositionY() > y){
                        for(CardVo temp2 : cardVos){
                            if(temp2.getPositionX() == x && temp2.getPositionY() > y
                            && temp2.getPositionY() <= temp.getPositionY()){
                                will_delete = false;
                                break;
                            }
                            will_delete = true;
                        }
                    }
                }
                if(will_delete == true){
                    Card card1 = new Card();
                    card1.setPositionX(temp.getPositionX());
                    card1.setPositionY(temp.getPositionY());
                    card1.setStoryId(temp.getStoryId());
                    cardRepository.delete(card1);
                }
            }
        }

        return true;
    }

}
