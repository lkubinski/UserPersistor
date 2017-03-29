package com.lukasz;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by LKubinski on 28/03/2017.
 */
public class UserDBLayer {

    private SessionFactory sessionFactory;

    public UserDBLayer() {
        sessionFactory = new HibernateUtil().buildSessionFactory();
    }

    //Added for dependency Injection
    public UserDBLayer(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    // ToDo  -change To action and inject
    public UserDB getUserByLoginName(String loginName) {
        Session session = sessionFactory.openSession();
        UserDB userdb = null;
        try{
            session.beginTransaction();
            Criteria criteria = session.createCriteria(UserDB.class);
            List<UserDB> users = criteria.add(Restrictions.eq("LOGIN_NAME", loginName)).list();
            if (users.size() > 1) {
                throw new IllegalArgumentException();
            }
            if(users.size() == 1){
                userdb = users.get(0);
            }
        }finally {
            session.close();
        }
        return userdb;
    }

    // To implement
    public UserDB getUser(String... userParams) {
        return null;
    }


    public void persist(UserDB userdb) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.save(userdb);
            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }
}
