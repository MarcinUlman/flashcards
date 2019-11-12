package dev.ulman.flashcards.Dao;

import dev.ulman.flashcards.model.Card;
import dev.ulman.flashcards.model.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class GroupDaoImpl implements GroupDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public GroupDaoImpl(EntityManagerFactory factory){
        if(factory.unwrap(SessionFactory.class) == null)
            throw new NullPointerException("factory is not a hibernate factory");

        sessionFactory =  factory.unwrap(SessionFactory.class);
    }

    @Override
    public List<Group> getAllGroups() {
        List<Group> groups = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
            Root<Group> root = criteriaQuery.from(Group.class);
            criteriaQuery.select(root);

            Query<Group> query = session.createQuery(criteriaQuery);

            groups = query.getResultList();

            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return groups;
    }

    @Override
    public Group getGroupById(int id) {
        Group group = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            group= session.get(Group.class, id);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction!= null) transaction.rollback();
        }
        return group;
    }

    @Override
    public Group getGroupByName(String groupName) {
        Group group = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
            Root<Group> root = criteriaQuery.from(Group.class);
            criteriaQuery.where(criteriaBuilder.equal(root.get("name"), groupName));
            Query<Group> query = session.createQuery(criteriaQuery);
            group = query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return group;
    }

    @Override
    public boolean addGroup(Group newGroup) {
        boolean success = false;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.save(newGroup);
            transaction.commit();
            success = true;
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return success;
    }

    @Override
    public boolean deleteGroup(int id) {
        boolean success = false;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Group group = session.get(Group.class, id);
            session.delete(group);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        return success;
    }

    @Override
    public boolean editGroup(int id, Group incomingGroup) {
        boolean success = false;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Group group = session.get(Group.class, id);
            group.setName(incomingGroup.getName());
            session.update(group);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return success;
    }

    @Override
    public List<Card> getAllCards(Group group) {
        List<Card> cardList = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
            Root<Group> root = criteriaQuery.from(Group.class);
            criteriaQuery.where(criteriaBuilder.equal(root.get("name"), group.getName()));
            Query<Group> query = session.createQuery(criteriaQuery);
            cardList = query.getSingleResult().getCards();
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return cardList;
    }
}
