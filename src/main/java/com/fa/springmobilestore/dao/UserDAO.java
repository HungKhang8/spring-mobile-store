package com.fa.springmobilestore.dao;

import com.fa.springmobilestore.entity.User;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public User findUser(String userName) {
        try {
            String sql = "SELECT u "
                    + "FROM " + User.class.getName() + " u "
                    + "WHERE u.userName = :userName ";
            Session session = this.sessionFactory.getCurrentSession();
            Query<User> query = session.createQuery(sql, User.class);
            query.setParameter("userName", userName);
            return (User) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
