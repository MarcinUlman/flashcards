package dev.ulman.flashcards.services;


import dev.ulman.flashcards.model.Card;
import dev.ulman.flashcards.model.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroups();
    Group getGroupById(int id);
    Group getGroupByName(String groupName);
    void addGroup(Card newGroup);
    void deleteGroup(int id);
    void editGroup(int id, Group incomingGroup);

    List<Card> getAllCards();
    List<Card> getRandomCards(int number);
    List<Card> getScopedCards(int minId, int maxId);

}
