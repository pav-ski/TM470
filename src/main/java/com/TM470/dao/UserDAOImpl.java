package com.TM470.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.TM470.dao.UserDAO;
import com.TM470.domain.LocationArea;
import com.TM470.domain.User;

@Component
public class UserDAOImpl implements UserDAO{
	
	
	
	
	
	
    private SessionFactory sessionFactory;
    
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    

    @Override
    @Transactional
    public List<User> list() {
        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listUser;
    }
	
    @Override
    @Transactional
    public void addUser(User user) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.persist(user);
    }
    
    @Override
    @Transactional
    public void updateUser(User user) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.update(user);
    }
    
    @Override
    @Transactional
    public User getUserById(int id) {
    	Session session = this.sessionFactory.getCurrentSession();
    	User user = (User) session.load(User.class, new Integer(id));
    	return user;
    }
    
    @Override
    @Transactional
    public void removeUser(int id) {
    	Session session = this.sessionFactory.getCurrentSession();
    	User user = (User) session.load(User.class, new Integer(id));
    	if(null != user) {
    		session.delete(user);
    	}
    	
    }

	
}
