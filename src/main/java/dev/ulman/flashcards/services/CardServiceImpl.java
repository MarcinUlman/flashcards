package dev.ulman.flashcards.services;

import dev.ulman.flashcards.Dao.CardDao;
import dev.ulman.flashcards.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    private final CardDao cardDao;

    @Autowired
    public CardServiceImpl(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    @Override
    public Card getCardById(int id) {
        return cardDao.getCardById(id);
    }

    @Override
    public boolean addCard(Card newCard) {
        return cardDao.addCard(newCard);
    }

    @Override
    public boolean deleteCard(int id) {
        return cardDao.deleteCard(id);
    }

    @Override
    public boolean updateCard(int id, Card incomingCard) {
        return cardDao.updateCard(id, incomingCard);
    }
}
