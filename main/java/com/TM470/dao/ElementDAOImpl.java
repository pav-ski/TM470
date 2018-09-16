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

@Repository
@EnableTransactionManagement
public class ElementDAOImpl implements ElementDAO{
	
		
		@Autowired
    	private SessionFactory sessionFactory;
	    
	    public ElementDAOImpl() {

	    }


	    @Override
	    @Transactional
	    public List <Element> list() {
	        @SuppressWarnings("unchecked")
	        List<Element> listElement = (List<Element>) sessionFactory.getCurrentSession()
	                .createCriteria(Element.class)
	                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	 
	        return listElement;
	    }
		
	    @Override
	    @Transactional
	    public void addElement(Element element) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.persist(element);
	    }
	    
	    @Override
	    @Transactional
	    public void updateElement(Element element) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.update(element);
	    }
	    
	    @Override
	    @Transactional
	    public Element getElementById(int id) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	Element element = (Element) session.load(Element.class, new Integer(id));
	    	return element;
	    }
	    
	    @Override
	    @Transactional
	    public void removeElement(int id) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	Element element = (Element) session.load(Element.class, new Integer(id));
	    	if(null != element) {
	    		session.delete(element);
	    	}
	    	
	    }
	    
	    @Override
	    @Transactional
	    public void refresh(Element element) {
	    	Session session = this.sessionFactory.getCurrentSession();
	    	session.refresh(element);

	    }
	    



	}


