package core.basesyntax.dao.impl;

import core.basesyntax.HibernateUtil;
import core.basesyntax.dao.CommentDao;
import core.basesyntax.model.Comment;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CommentDaoImpl implements CommentDao {
    private final SessionFactory sessionFactory;

    public CommentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public CommentDaoImpl() {
        this(HibernateUtil.getSessionFactory());
    }

    @Override
    public Comment create(Comment entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(entity);
        tx.commit();
        session.close();
        return entity;
    }

    @Override
    public Comment get(Long id) {
        Session session = sessionFactory.openSession();
        Comment comment = session.get(Comment.class, id);
        session.close();
        return comment;
    }

    @Override
    public List<Comment> getAll() {
        Session session = sessionFactory.openSession();
        List<Comment> list = session.createQuery("from Comment", Comment.class)
                .getResultList();
        session.close();
        return list;
    }

    @Override
    public void remove(Comment entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.remove(entity);
        tx.commit();
        session.close();
    }
}

