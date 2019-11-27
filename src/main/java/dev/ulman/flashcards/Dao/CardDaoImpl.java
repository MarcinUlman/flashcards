package dev.ulman.flashcards.Dao;

import dev.ulman.flashcards.model.Card;
import dev.ulman.flashcards.model.Group;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public class CardDaoImpl implements CardDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public CardDaoImpl(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @Override
    public Card getCardById(int id) {
        Card card = null;
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            card=session.get(Card.class, id);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return card;
    }

    @Override
    public boolean addCard(Card newCard) {
        boolean success = false;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Group group = session.get(Group.class, newCard.getGroup().getId());
            newCard.setGroup(group);
            Hibernate.initialize(group.getCards());
            group.getCards().add(newCard);
            session.saveOrUpdate(group);
            transaction.commit();
            success = true;
        } catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
        return success;
    }

    @Override
    public boolean deleteCard(int id) {
        boolean success = false;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Card card = session.get(Card.class, id);
            session.delete(card);
            transaction.commit();
            success = true;
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return success;
    }

    @Override
    public boolean updateCard(int id, Card incomingCard) {
        boolean success = false;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Card card = session.get(Card.class, id);

            Group oldGroup = session.get(Group.class, card.getGroup().getId());
            Hibernate.initialize(oldGroup.getCards());
            oldGroup.getCards().remove(card);
            session.saveOrUpdate(oldGroup);

            card.setWord(incomingCard.getWord());
            card.setTranslation(incomingCard.getTranslation());
            try {
                card.setDescription(incomingCard.getDescription());
                card.setImageURL(incomingCard.getImageURL());
            } catch (NullPointerException e){
                //do nothing this fields can be null
            }

            Group newGroup = session.get(Group.class, incomingCard.getGroup().getId());
            Hibernate.initialize(newGroup.getCards());
            newGroup.getCards().add(card);
            card.setGroup(newGroup);
            session.saveOrUpdate(newGroup);

            transaction.commit();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return success;
    }
}