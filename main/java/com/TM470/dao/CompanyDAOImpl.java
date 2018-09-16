package com.TM470.dao;



import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.TM470.domain.Company;

@Repository
@EnableTransactionManagement
public class CompanyDAOImpl implements CompanyDAO{
	
	@Autowired
    private SessionFactory sessionFactory;
    
    public CompanyDAOImpl() {

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
