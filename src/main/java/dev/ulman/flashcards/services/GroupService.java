package dev.ulman.flashcards.services;


import dev.ulman.flashcards.model.Card;
import dev.ulman.flashcards.model.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroups();
    Group getGroupById(int id);
    Group getGroupByName(String groupName);
    boolean addGroup(Group newGroup);
    boolean deleteGroup(int id);
    boolean editGroup(int id, Group incomingGroup);

    List<Card> getAllCards(Group group);
    List<Card> getRandomCards(Group group, int number);
    List<Card> getScopedCards(Group group, int minId, int maxId);

}
