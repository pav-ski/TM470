package com.TM470.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.TM470.domain.Job;
import com.TM470.domain.Location;
import com.TM470.domain.Update;

@Repository
@EnableTransactionManagement
public class UpdateDAOImpl implements UpdateDAO{
	
		
		@Autowired
    	private SessionFactory sessionFactory;
	    
	    public UpdateDAOImpl() {

	    }
	    

	    @Override
	    @Transactional
	    public List<Update> list() {
	        @SuppressWarnings("unchecked")
	        List<Update> listUpdate = (List<Update>) sessionFactory.getCurrentSession()
	                .createCriteria(Update.class)
	                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	 
	        return listUpdate;
	    }
		
	    @Override
	    @Transactional
	    public void addUpdate(Update update) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.persist(update);
	    }
	    
	    @Override
	    @Transactional
	    public void updateUpdate(Update update) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.update(update);
	    }
	    
	    @Override
	    @Transactional
	    public Location getUpdateById(int id) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	Location location = (Location) session.load(Location.class, new Integer(id));
	    	return location;
	    }
	    
	    @Override
	    @Transactional
	    public void removeUpdate(int id) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	Update update = (Update) session.load(Update.class, new Integer(id));
	    	if(null != update) {
	    		session.delete(update);
	    	}
	    	
	    }
	    
	    @Override
	    @Transactional
	    public void saveOrUpdate(Update update) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.saveOrUpdate(update);
	    }


	}


