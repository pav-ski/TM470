package com.TM470.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.TM470.domain.Location;

@Repository
@EnableTransactionManagement
public class LocationDAOImpl implements LocationDAO{
	
		
	    private SessionFactory sessionFactory;
	    
	    public LocationDAOImpl(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }
	    

	    @Override
	    @Transactional
	    public List<Location> list() {
	        @SuppressWarnings("unchecked")
	        List<Location> listLocation = (List<Location>) sessionFactory.getCurrentSession()
	                .createCriteria(Location.class)
	                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	 
	        return listLocation;
	    }
		
	    @Override
	    @Transactional
	    public void addLocation(Location location) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.persist(location);
	    }
	    
	    @Override
	    @Transactional
	    public void updateLocation(Location location) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.update(location);
	    }
	    
	    @Override
	    @Transactional
	    public Location getLocationById(int id) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	Location location = (Location) session.load(Location.class, new Integer(id));
	    	return location;
	    }
	    
	    @Override
	    @Transactional
	    public void removeLocation(int id) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	Location location = (Location) session.load(Location.class, new Integer(id));
	    	if(null != location) {
	    		session.delete(location);
	    	}
	    	
	    }


	}


