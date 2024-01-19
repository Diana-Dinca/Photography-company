package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Fotografi;
import app.repository.FotografiRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class FotografiRepositoryImpl implements FotografiRepository {
    @Override
    public Fotografi save(Fotografi entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idClientSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idClientSaved);
    }

    @Override
    public Fotografi update(Fotografi entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();

        return findById(entity.getId());
    }

    @Override
    public Fotografi findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // HQL - Hibernate Query Language, but use named query instead to reuse them
        // Query query = session.createQuery("from Adresa where id=:id");
        // query.setParameter("id", id);

        TypedQuery<Fotografi> query = session.getNamedQuery("findFotografById");
        query.setParameter("id", id);

        Fotografi event;
        try {
            event = (Fotografi) query.getSingleResult();
        } catch (NoResultException e) {
            event = null;
        }

        transaction.commit();
        session.close();

        return event;
    }

    @Override
    public List<Fotografi> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Fotografi> query = session.createNamedQuery("findAllFotografi", Fotografi.class);
        List<Fotografi> foto = query.getResultList();

        transaction.commit();
        session.close();
        return foto;
    }

    @Override
    public boolean delete(Fotografi entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getId();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }
    @Override
    public boolean deleteById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Fotografi fotoToDel= session.get(Fotografi.class, id);
        if(fotoToDel != null)
            session.delete(fotoToDel);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }
    @Override
    public Fotografi findByName(String name) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Used a Named Query - best solution
        TypedQuery<Fotografi> query = session.getNamedQuery("findFotografByName");
        query.setParameter("name", name);
        Fotografi foto;
        try {
            foto = query.getSingleResult();
        } catch (NoResultException e) {
            foto = null;
        }
        transaction.commit();
        session.close();

        return foto;
    }
}
