package dev.ulman.flashcards.api;

import dev.ulman.flashcards.model.Card;

import java.util.List;

public interface CardService {

    List<Card> getAllCards();
    List<Card> getRandomCards(int number);
    List<Card> getScopedCards(int minId, int maxId);
    List<Card> getWholeGropuCards(String groupName);
    Card getCardById(int id);
    void addCard(Card newCard);
    void deleteCard(int id);
    void editCard(int id, Card incomingCard);

}
