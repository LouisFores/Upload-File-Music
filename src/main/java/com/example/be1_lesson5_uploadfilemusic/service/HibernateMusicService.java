package com.example.be1_lesson5_uploadfilemusic.service;

import com.example.be1_lesson5_uploadfilemusic.model.Music;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class HibernateMusicService implements IMusicService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Music> findAll() {
        String queryStr = "Select m from Music as m";
        TypedQuery<Music> query = entityManager.createQuery(queryStr, Music.class);
        return query.getResultList();
    }

    @Override
    public void save(Music music) {
        Transaction transaction = null;
        Music origin;
        if (music.getId() == 0) {
            origin = new Music();
        } else {
            origin = findById(music.getId());
        }
        try (Session session = sessionFactory.openSession()) {
             transaction =session.beginTransaction();
             origin.setName(music.getName());
             origin.setAuthor(music.getAuthor());
             origin.setKind(music.getKind());
             origin.setUrl(music.getUrl());
             session.saveOrUpdate(origin);
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Music findById(int id) {
        String queryStr = "Select m From Music As m Where m.id = :id";
        TypedQuery<Music> query = entityManager.createQuery(queryStr, Music.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void remove(int id) {
        Music music = findById(id);
        if (music != null) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()){
                transaction = session.beginTransaction();
                session.remove(music);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }
}
