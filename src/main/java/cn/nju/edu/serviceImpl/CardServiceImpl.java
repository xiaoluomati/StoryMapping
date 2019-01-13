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
    public List<CardVo> getCardList(String storyName,int userId) {
        List<Card> cards = cardRepository.findByStoryNameAndUserId(storyName,userId);
        List<CardVo> cardVos = new ArrayList<>();
        for(int i = 0;i < cards.size();i++){
            Card temp = cards.get(i);
            CardVo cardVo = new CardVo();
            cardVo.setContent(temp.getContent());
            cardVo.setState(CardState.values()[temp.getState()]);
            cardVo.setCost(temp.getCost());
            cardVo.setPositionX(temp.getPositionX());
            cardVo.setPositionY(temp.getPositionY());
            cardVo.setStoryName(temp.getStoryName());
            cardVo.setUserId(temp.getUserId());
            int x= temp.getPositionX();
            cardVo.setType(CardType.values()[x > 2 ? 2 : x]);
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
        card.setStoryName(cardVo.getStoryName());
        card.setUserId(cardVo.getUserId());
        cardRepository.save(card);
        return true;
    }

    @Override
    public boolean deleteCard(CardVo cardVo) {
        Card card = new Card();
        card.setContent(cardVo.getContent());
        card.setState(cardVo.getState().ordinal());
        card.setCost(cardVo.getCost());
        card.setPositionX(cardVo.getPositionX());
        card.setPositionY(cardVo.getPositionY());
        card.setStoryName(cardVo.getStoryName());
        card.setUserId(cardVo.getUserId());
        cardRepository.delete(card);
        return true;
    }
}
