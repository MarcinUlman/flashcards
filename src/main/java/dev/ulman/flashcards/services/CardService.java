package dev.ulman.flashcards.services;

import dev.ulman.flashcards.model.Card;

public interface CardService {

    Card getCardById(int id);
    void addCard(Card newCard);
    void deleteCard(int id);
    void updateCard(int id, Card incomingCard);

}
