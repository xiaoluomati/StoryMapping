package cn.nju.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import cn.nju.edu.entity.Card;
import cn.nju.edu.entity.CardKey;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, CardKey> {

    List<Card> findByStoryId(int storyId);

    Card findByCardId(int cardId);

    @Transactional
    @Modifying
    @Query("delete from Card where cardId = :cardId")
    void deleteByCardId(@Param("cardId") int cardId);

}
