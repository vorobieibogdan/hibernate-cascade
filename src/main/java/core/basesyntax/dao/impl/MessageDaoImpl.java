package core.basesyntax.dao.impl;

import core.basesyntax.HibernateUtil;
import core.basesyntax.dao.MessageDao;
import core.basesyntax.model.Message;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MessageDaoImpl implements MessageDao {
    private final SessionFactory sessionFactory;

    public MessageDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public MessageDaoImpl() {
        this(HibernateUtil.getSessionFactory());
    }

    @Override
    public Message create(Message entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(entity);
        tx.commit();
        session.close();
        return entity;
    }

    @Override
    public Message get(Long id) {
        Session session = sessionFactory.openSession();
        Message message = session.get(Message.class, id);
        session.close();
        return message;
    }

    @Override
    public List<Message> getAll() {
        Session session = sessionFactory.openSession();
        List<Message> list = session.createQuery("from Message", Message.class)
                .getResultList();
        session.close();
        return list;
    }

    @Override
    public void remove(Message entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.remove(entity);
        tx.commit();
        session.close();
    }
}

