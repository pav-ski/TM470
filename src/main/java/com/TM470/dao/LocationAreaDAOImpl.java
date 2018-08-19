package com.TM470.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


import com.TM470.domain.LocationArea;

@Repository
@EnableTransactionManagement
public class LocationAreaDAOImpl implements LocationAreaDAO{
	
		
	    private SessionFactory sessionFactory;
	    
	    public LocationAreaDAOImpl(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }
	    

	    @Override
	    @Transactional
	    public List<LocationArea> list() {
	        @SuppressWarnings("unchecked")
	        List<LocationArea> listLocationArea = (List<LocationArea>) sessionFactory.getCurrentSession()
	                .createCriteria(LocationArea.class)
	                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	 
	        return listLocationArea;
	    }
		
	    @Override
	    @Transactional
	    public void addLocationArea(LocationArea location) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.persist(location);
	    }
	    
	    @Override
	    @Transactional
	    public void updateLocationArea(LocationArea location) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.update(location);
	    }
	    
	    @Override
	    @Transactional
	    public LocationArea getLocationAreaById(int id) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	LocationArea location = (LocationArea) session.load(LocationArea.class, new Integer(id));
	    	return location;
	    }
	    
	    @Override
	    @Transactional
	    public void removeLocationArea(int id) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	LocationArea location = (LocationArea) session.load(LocationArea.class, new Integer(id));
	    	if(null != location) {
	    		session.delete(location);
	    	}
	    	
	    }


	}


