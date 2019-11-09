package dev.ulman.flashcards.services;

import dev.ulman.flashcards.model.Card;
import dev.ulman.flashcards.model.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Override
    public List<Group> getAllGroups() {
        return null;
    }

    @Override
    public Group getGroupById(int id) {
        return null;
    }

    @Override
    public Group getGroupByName(String groupName) {
        return null;
    }

    @Override
    public void addGroup(Card newGroup) {

    }

    @Override
    public void deleteGroup(int id) {

    }

    @Override
    public void editGroup(int id, Group incomingGroup) {

    }

    @Override
    public List<Card> getAllCards() {
        return null;
    }

    @Override
    public List<Card> getRandomCards(int number) {
        return null;
    }

    @Override
    public List<Card> getScopedCards(int minId, int maxId) {
        return null;
    }
}
