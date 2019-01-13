package cn.nju.edu.repository;

import cn.nju.edu.entity.Card;
import cn.nju.edu.entity.CardKey;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CardRepositoryTest {
    @Autowired
    CardRepository cardRepository;

    @Test
    public void findById(){
        CardKey id = new CardKey();
        id.setPositionX(5);
        id.setPositionY(1);
        id.setStoryId(1);

        Card card = cardRepository.findById(id).get();
        Assert.assertNotNull(card);
        Assert.assertEquals(card.getContent(),"awayz is a rbq");
    }

    @Test
    public void finfByCardId(){
        Card card = cardRepository.findByCardId(1);

        Assert.assertNotNull(card);
        Assert.assertEquals(card.getContent(),"awayz is a rbq");
    }

    @Test
    public void save(){
        Card card = new Card();
        card.setContent("gaygayxiong");
        card.setState(1);
        card.setCost(38);
        card.setPositionX(3);
        card.setPositionY(8);
        card.setStoryId(1);
        cardRepository.save(card);

        CardKey id = new CardKey();
        id.setPositionX(3);
        id.setPositionY(8);
        id.setStoryId(1);

        Card card1 = cardRepository.findById(id).get();
        Assert.assertNotNull(card);
        Assert.assertEquals(card.getContent(),"gaygayxiong");
    }

    @Test
    public void findByStoryId(){
        Card card = new Card();
        card.setContent("gaygayxiong");
        card.setState(1);
        card.setCost(38);
        card.setPositionX(3);
        card.setPositionY(8);
        card.setStoryId(1);
        cardRepository.save(card);

        String content = "";
        List<Card> cards = cardRepository.findByStoryId(1);
        for(int i = 0;i < cards.size();i++){
            Card temp = cards.get(i);
            if(temp.getStoryId() == 1 && temp.getPositionX() == 3 && temp.getPositionY() == 8){
                content = temp.getContent();
            }
        }
        Assert.assertNotNull(content);
        Assert.assertEquals(content,"gaygayxiong");
    }

    @Test
    public void update(){
        CardKey id = new CardKey();
        id.setPositionX(5);
        id.setPositionY(1);
        id.setStoryId(1);

        Card card = cardRepository.findById(id).get();
        card.setContent("awayz is still a rbq");
        cardRepository.save(card);

        Card card1 = cardRepository.findById(id).get();
        Assert.assertNotNull(card1);
        Assert.assertEquals(card1.getContent(),"awayz is still a rbq");
    }

    @Test
    public void delete(){
        CardKey id = new CardKey();
        id.setPositionX(5);
        id.setPositionY(1);
        id.setStoryId(1);

        Card card = cardRepository.findById(id).get();
        cardRepository.delete(card);

        boolean isDeleted = false;

        try {
            Card card1 = cardRepository.findById(id).get();
        }
        catch (NoSuchElementException e){
            isDeleted = true;
        }

        Assert.assertEquals(isDeleted,true);
    }


}
