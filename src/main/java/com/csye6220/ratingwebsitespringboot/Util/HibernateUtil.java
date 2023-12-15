package com.csye6220.ratingwebsitespringboot.Util;

import com.csye6220.ratingwebsitespringboot.Entity.Anime;
import com.csye6220.ratingwebsitespringboot.Entity.Operation;
import com.csye6220.ratingwebsitespringboot.Entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtil {
    public static final ThreadLocal sessionTread =new ThreadLocal();

    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = getSettings();

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Anime.class);
                configuration.addAnnotatedClass(Operation.class);

                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    private static Properties getSettings() {
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/RatingWebsiteDB");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "91131723");

        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.POOL_SIZE, "3");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.FORMAT_SQL, "true");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        return settings;
    }

    public static Session getSession() throws HibernateException {
        Session s = (Session) sessionTread.get();
        if(s == null) {
            s = getSessionFactory().openSession();
            sessionTread.set(s);
        } else if(!s.isOpen()) {
            s = getSessionFactory().openSession();
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
