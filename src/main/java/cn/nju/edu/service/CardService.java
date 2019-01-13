package cn.nju.edu.service;

import cn.nju.edu.vo.CardVo;

import java.util.List;

public interface CardService {
    List<CardVo> getCardList(String storyName,int userId);

    boolean addCard(CardVo cardVo);

    boolean deleteCard(CardVo cardVo);
}
