package com.csye6220.ratingwebsitespringboot.DAO.implementations;

import com.csye6220.ratingwebsitespringboot.DAO.interfaces.AnimeDAO;
import com.csye6220.ratingwebsitespringboot.Entity.Anime;
import com.csye6220.ratingwebsitespringboot.Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimeDAOImpl implements AnimeDAO {
    @Override
    public List<Anime> getAllAnimes() {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        List<Anime> list = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Anime");
            list = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

    @Override
    public Anime getAnimeById(int id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        List<Anime> list = null;
        Anime res = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Anime A WHERE A.id = :anime_id");
            query.setParameter("anime_id", id);
            list = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        if(list.size()>0) {
            res = list.get(0);
        }
        return res;
    }

    @Override
    public Anime getAnimeByName(String name) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        List<Anime> list = null;
        Anime res = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Anime A WHERE A.anime_name = :anime_name");
            query.setParameter("anime_name", name);
            list = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        if(list.size()>0) {
            res = list.get(0);
        }
        return res;
    }

    @Override
    public void saveAnime(Anime anime) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(anime);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteAnime(int id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Anime A WHERE A.id = :anime_id");
            query.setParameter("anime_id", id);
            int result = query.executeUpdate();
            System.out.println(result);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
