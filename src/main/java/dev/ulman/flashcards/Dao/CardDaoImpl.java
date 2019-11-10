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
    public CardDaoImpl(EntityManagerFactory factory){
        if(factory.unwrap(SessionFactory.class) == null)
            throw new NullPointerException("factory is not a hibernate factory");

        sessionFactory =  factory.unwrap(SessionFactory.class);
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
    public void addCard(Card newCard) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.save(newCard);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        }
    }

    @Override
    public void deleteCard(int id) {
        Card card = getCardById(id);
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            session.delete(card);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void updateCard(int id, Card incomingCard) {
        Card card = getCardById(id);
        card.setDescription(incomingCard.getDescription());
        card.setGroup(incomingCard.getGroup());
        card.setImageURL(incomingCard.getImageURL());
        card.setWord(incomingCard.getWord());
        card.setTranslation(incomingCard.getTranslation());

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.update(card);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }
}
