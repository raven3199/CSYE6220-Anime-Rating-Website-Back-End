package com.csye6220.ratingwebsitespringboot.DAO.implementations;

import com.csye6220.ratingwebsitespringboot.DAO.interfaces.UserDAO;
import com.csye6220.ratingwebsitespringboot.Entity.User;
import com.csye6220.ratingwebsitespringboot.Util.HibernateUtil;

import org.hibernate.*;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        List<User> list = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM User");
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
    public User getUserById(int id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        List<User> list = null;
        User res = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM User U WHERE U.id = :user_id");
            query.setParameter("user_id", id);
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
    public User getUserByUsername(String username) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        List<User> list = null;
        User res = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM User U WHERE U.username = :username");
            query.setParameter("username", username);
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
    public void saveUser(User user) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(user);
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
    public void deleteUser(int id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User WHERE id = :user_id");
            query.setParameter("user_id", id);
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
