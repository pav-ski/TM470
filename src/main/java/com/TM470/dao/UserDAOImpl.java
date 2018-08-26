package com.TM470.dao;

import java.awt.print.Book;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.TM470.domain.Guest;
import com.TM470.domain.Staff;
import com.TM470.domain.User;

@Component
public class UserDAOImpl implements UserDAO{
	
	
	
	
	
	@Autowired
    private SessionFactory sessionFactory;
	
	
	
    
    public UserDAOImpl() {
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
    
    @Override
    @Transactional
    public Staff getStaff(int id) {
    	Session session = this.sessionFactory.getCurrentSession();
    	Staff staff = (Staff) session.load(Staff.class, new Integer(id));
    	return staff;
    }
    
    @Override
    @Transactional
    public Guest getGuest(int id) {
    	Session session = this.sessionFactory.getCurrentSession();
    	Guest guest = (Guest) session.load(Guest.class, new Integer(id));
    	return guest;
    }
    

	
}
