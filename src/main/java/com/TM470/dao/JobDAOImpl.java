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


@Repository
@EnableTransactionManagement
public class JobDAOImpl implements JobDAO{
	

		@Autowired
		private SessionFactory sessionFactory;
	    
	    public JobDAOImpl() {
	    	
	    }
	    
	    //public JobDAOImpl(SessionFactory sessionFactory) {
	    //    this.sessionFactory = sessionFactory;
	    //}
	    

	    @Override
	    @Transactional
	    public List<Job> list() {
	        @SuppressWarnings("unchecked")
	        List<Job> listJob = (List<Job>) sessionFactory.getCurrentSession()
	                .createCriteria(Job.class)
	                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	 
	        return listJob;
	    }
		
	    
	    @Override
	    @Transactional
	    public void addJob(Job job) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.persist(job);
	    }
	    
	    
	    @Override
	    @Transactional
	    public void updateJob(Job job) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.update(job);
	    }
	    
	    
	    @Override
	    @Transactional
	    public Job getJobById(int id) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	Job job = (Job) session.load(Job.class, new Integer(id));
	    	return job;
	    }
	    
	    @Override
	    @Transactional
	    public void removeJob(int id) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	Job job = (Job) session.load(Job.class, new Integer(id));
	    	if(null != job) {
	    		session.delete(job);
	    	}
	    	
	    }
	    
	    @Override
	    @Transactional
	    public void saveOrUpdate(Job job) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.saveOrUpdate(job);
	    }


	}


