package dev.ulman.flashcards.Dao;

import dev.ulman.flashcards.model.Card;
import dev.ulman.flashcards.model.Group;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class CardDaoImpl implements CardDao {

    private Set<Card> cardSet;

    public CardDaoImpl() {
        Group group = new Group("kolory");
        group.setId(1);

        Card card1 = new Card("biały", "white", "kolor biały",
                "https://images.obi.pl/product/PL/1152x744/217821_2.jpg",
                group);
        card1.setId(1);
        Card card2 = new Card("czarny", "black", "kolor czarny",
                "https://wszystkiesymbole.pl/wp-content/uploads/2018/06/kolor-czarny.jpg",
                group);
        card2.setId(2);
        Card card3 = new Card("niebieski", "blue", "kolor niebieski",
                "https://www.google.com/https://taniareklama.pl/environment/cache/images/500_500_productGfx_b7c3dd37fa5f85e3678e9c8e330f0b03.jpg",
                group);
        card3.setId(3);

        cardSet = new HashSet<>();

        cardSet.add(card1);
        cardSet.add(card2);
        cardSet.add(card3);
    }

    @Override
    public Card getCardById(int id) {
        return cardSet.stream()
                .filter(c -> c.getId() == id)
                .findFirst().get();
    }

    @Override
    public void addCard(Card newCard) {
    cardSet.add(newCard);
    }

    @Override
    public void deleteCard(int id) {
        cardSet.removeIf(c -> c.getId() == id);
    }

    @Override
    public void updateCard(int id, Card incomingCard) {
        deleteCard(id);
        incomingCard.setId(id);
        addCard(incomingCard);

    }
}
