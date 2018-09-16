package com.TM470.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.TM470.domain.Element;
import com.TM470.domain.Notification;

@Repository
@EnableTransactionManagement
public class NotificationDAOImpl implements NotificationDAO{
	
		
		@Autowired
    	private SessionFactory sessionFactory;
	    
	    public NotificationDAOImpl() {

	    }


	    @Override
	    @Transactional
	    public List <Notification> list() {
	        @SuppressWarnings("unchecked")
	        List<Notification> listNotification = (List<Notification>) sessionFactory.getCurrentSession()
	                .createCriteria(Notification.class)
	                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	 
	        return listNotification;
	    }
		
	    @Override
	    @Transactional
	    public void addNotification(Notification notification) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.persist(notification);
	    }
	    
	    @Override
	    @Transactional
	    public void updateNotification(Notification notification) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.update(notification);
	    }
	    
	    @Override
	    @Transactional
	    public Notification getNotificationById(int id) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	Notification notification = (Notification) session.load(Notification.class, new Integer(id));
	    	return notification;
	    }
	    
	    @Override
	    @Transactional
	    public void removeNotification(int id) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	Notification notification = (Notification) session.load(Notification.class, new Integer(id));
	    	if(null != notification) {
	    		session.delete(notification);
	    	}
	    	
	    }
	    
	    @Override
	    @Transactional
	    public void refresh(Notification notification) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.refresh(notification);

	    }
	    
	    @Override
	    @Transactional
	    public void saveOrUpdate(Notification notification) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.saveOrUpdate(notification);
	    }
	    



	}


