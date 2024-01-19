package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Adresa;
import app.repository.AdresaRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class AdresaRepositoryImpl implements AdresaRepository {
    @Override
    public Adresa save(Adresa entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer idAdrSaved = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(idAdrSaved);
    }

    @Override
    public Adresa update(Adresa entity) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();

        return findById(entity.getId());
    }

    @Override
    public Adresa findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // HQL - Hibernate Query Language, but use named query instead to reuse them
        // Query query = session.createQuery("from Adresa where id=:id");
        // query.setParameter("id", id);

       TypedQuery<Adresa> query = session.getNamedQuery("findAdrById");
       query.setParameter("id", id);

        Adresa adr;
        try {
            adr = (Adresa) query.getSingleResult();
        } catch (NoResultException e) {
            adr = null;
        }

        transaction.commit();
        session.close();

        return adr;
    }

    @Override
    public List<Adresa> findAll() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Adresa> query = session.createNamedQuery("findAllAdr", Adresa.class);
        List<Adresa> adrese = query.getResultList();

        transaction.commit();
        session.close();
        return adrese;
    }

    @Override
    public boolean delete(Adresa entity) {
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

        Adresa adrToDel= session.get(Adresa.class, id);
        if(adrToDel != null)
             session.delete(adrToDel);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }
    @Override
    public Adresa findByName(String name) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Used a Named Query - best solution
        TypedQuery<Adresa> query = session.getNamedQuery("findAdrByCity");
        query.setParameter("name", name);
        Adresa adr;
        try {
            adr = query.getSingleResult();
        } catch (NoResultException e) {
            adr = null;
        }
        transaction.commit();
        session.close();

        return adr;
    }
}
