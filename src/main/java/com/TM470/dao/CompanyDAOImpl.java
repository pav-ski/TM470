package com.TM470.dao;



import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.TM470.domain.Company;

@Repository
@EnableTransactionManagement
public class CompanyDAOImpl implements CompanyDAO{
	
    private SessionFactory sessionFactory;
    
    public CompanyDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    

    @Override
    @Transactional
    public List<Company> list() {
        @SuppressWarnings("unchecked")
        List<Company> listCompany = (List<Company>) sessionFactory.getCurrentSession()
                .createCriteria(Company.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listCompany;
    }
	


}
