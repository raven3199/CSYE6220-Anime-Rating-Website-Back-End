package com.csye6220.ratingwebsitespringboot.Util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static final ThreadLocal sessionTread =new ThreadLocal();

    public static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Fail to create SessionFactory object");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        Session s = (Session) sessionTread.get();
        if(s == null) {
            s = sessionFactory.openSession();
            sessionTread.set(s);
        } else if(!s.isOpen()) {
            s = sessionFactory.openSession();
            sessionTread.set(s);
        }
        return s;
    }

    public static void closeSession() throws HibernateException {
        Session s = (Session) sessionTread.get();
        if(s != null) {
            s.close();
        }
        sessionTread.set(null);
    }
}
