package core.basesyntax.dao.impl;

import core.basesyntax.HibernateUtil;
import core.basesyntax.dao.MessageDetailsDao;
import core.basesyntax.model.MessageDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MessageDetailsDaoImpl implements MessageDetailsDao {
    private final SessionFactory sessionFactory;

    public MessageDetailsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public MessageDetailsDaoImpl() {
        this(HibernateUtil.getSessionFactory());
    }

    @Override
    public MessageDetails create(MessageDetails entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(entity);
        tx.commit();
        session.close();
        return entity;
    }

    @Override
    public MessageDetails get(Long id) {
        Session session = sessionFactory.openSession();
        MessageDetails details = session.get(MessageDetails.class, id);
        session.close();
        return details;
    }
}

