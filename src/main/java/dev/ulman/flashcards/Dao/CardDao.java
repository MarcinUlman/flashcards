package dev.ulman.flashcards.Dao;

import dev.ulman.flashcards.model.Card;

public interface CardDao {

    Card getCardById(int id);
    boolean addCard(Card newCard);
    boolean deleteCard(int id);
    boolean updateCard(int id, Card incomingCard);
}
