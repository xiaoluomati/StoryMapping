package cn.nju.edu.repository;

import org.springframework.data.repository.CrudRepository;
import cn.nju.edu.entity.Card;
import cn.nju.edu.entity.CardKey;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, CardKey>{

    List<Card> findByStoryId(int storyId);

    Card findByCardId(int cardId);
}
