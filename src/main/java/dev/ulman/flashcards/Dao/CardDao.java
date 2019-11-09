package dev.ulman.flashcards.Dao;

import dev.ulman.flashcards.model.Card;

public interface CardDao {

    Card getCardById(int id);
    void addCard(Card newCard);
    void deleteCard(int id);
    void updateCard(int id, Card incomingCard);
}
