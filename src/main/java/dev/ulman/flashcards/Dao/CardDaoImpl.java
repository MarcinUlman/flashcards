package dev.ulman.flashcards.Dao;

import dev.ulman.flashcards.model.Card;
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
            session.save(newCard);
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
            card.setDescription(incomingCard.getDescription());
            card.setGroup(incomingCard.getGroup());
            card.setImageURL(incomingCard.getImageURL());
            card.setWord(incomingCard.getWord());
            card.setTranslation(incomingCard.getTranslation());
            session.update(card);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return success;
    }
}