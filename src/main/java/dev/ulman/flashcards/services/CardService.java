package dev.ulman.flashcards.services;

import dev.ulman.flashcards.model.Card;

public interface CardService {

    Card getCardById(int id);
    boolean addCard(Card newCard);
    boolean deleteCard(int id);
    boolean updateCard(int id, Card incomingCard);

}
