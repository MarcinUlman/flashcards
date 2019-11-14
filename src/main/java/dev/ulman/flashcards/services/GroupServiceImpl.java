package dev.ulman.flashcards.services;

import dev.ulman.flashcards.Dao.GroupDao;
import dev.ulman.flashcards.model.Card;
import dev.ulman.flashcards.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    @Autowired
    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }


    @Override
    public List<Group> getAllGroups() {
        return groupDao.getAllGroups();
    }

    @Override
    public Group getGroupById(int id) {
        return groupDao.getGroupById(id);
    }

    @Override
    public Group getGroupByName(String groupName) {
        return groupDao.getGroupByName(groupName);
    }

    @Override
    public boolean addGroup(Group newGroup) {
        return groupDao.addGroup(newGroup);
    }

    @Override
    public boolean deleteGroup(int id) {
        return groupDao.deleteGroup(id);
    }

    @Override
    public boolean editGroup(int id, Group incomingGroup) {
       return groupDao.editGroup(id, incomingGroup);
    }

    @Override
    public List<Card> getAllCards(Group group) {
        return groupDao.getAllCards(group);
    }

    @Override
    public List<Card> getRandomCards(Group group, int number) {
        return null;
    }

    @Override
    public List<Card> getScopedCards(Group group, int minId, int maxId) {
        return null;
    }
}
