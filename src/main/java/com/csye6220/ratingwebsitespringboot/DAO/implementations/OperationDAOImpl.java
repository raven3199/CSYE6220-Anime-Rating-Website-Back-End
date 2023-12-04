package com.csye6220.ratingwebsitespringboot.DAO.implementations;

import com.csye6220.ratingwebsitespringboot.DAO.interfaces.OperationDAO;
import com.csye6220.ratingwebsitespringboot.Entity.Anime;
import com.csye6220.ratingwebsitespringboot.Entity.Operation;
import com.csye6220.ratingwebsitespringboot.Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OperationDAOImpl implements OperationDAO {
    @Override
    public List<Operation> getAllOperations() {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        List<Operation> list = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Operation");
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
    public Operation getOperationById(int id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        List<Operation> list = null;
        Operation res = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Operation O WHERE O.id = :operation_id");
            query.setParameter("operation_id", id);
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
    public Operation getOperationByUser(int user_id, int anime_id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        List<Operation> list = null;
        Operation res = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Operation O WHERE O.user_id = :user_id AND O.anime_id = :anime_id");
            query.setParameter("user_id", user_id);
            query.setParameter("anime_id", anime_id);
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
    public void saveOperation(Operation operation) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(operation);
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
