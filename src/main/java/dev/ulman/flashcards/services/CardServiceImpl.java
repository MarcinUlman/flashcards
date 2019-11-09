package dev.ulman.flashcards.services;

import dev.ulman.flashcards.Dao.CardDao;
import dev.ulman.flashcards.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    private CardDao cardDao;

    @Autowired
    public CardServiceImpl(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    @Override
    public Card getCardById(int id) {
        return cardDao.getCardById(id);
    }

    @Override
    public void addCard(Card newCard) {
        cardDao.addCard(newCard);
    }

    @Override
    public void deleteCard(int id) {
        cardDao.deleteCard(id);
    }

    @Override
    public void updateCard(int id, Card incomingCard) {
        cardDao.updateCard(id, incomingCard);
    }
}
