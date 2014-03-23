package org.celts.db.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.Years;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("yearService")
@Transactional
public class YearService {
	
protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Retrieves all student volunteers
	 */
	@SuppressWarnings("unchecked")
	public List<Years> getAll20122013() {
		logger.debug("Retrieving all semesters");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Years y where y.yr='2012' OR y.yr='2013' OR y.yr='<Select>'");
		
		
		// Retrieve all
		return  query.list();
	}
	
	
	
	/**
	 * Retrieves all student volunteers
	 */
	@SuppressWarnings("unchecked")
	public List<Years> getAll() {
		logger.debug("Retrieving all semesters");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Years");
		
		
		// Retrieve all
		return  query.list();
	}
	
	
	
	
	/**
	 * Retrieves a single student semester
	 */
	public Years get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers
		Years years = (Years) session.get(Years.class, id);
		
		// Persists to db
		return years;
	}
	
	
	/**
	 * Adds a new semester
	 */
	public void add(Years years) {
		logger.debug("Adding new semester");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Persists to db
		session.save(years);
	}
	
	/**
	 * Deletes an existing semester
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing student semester");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student semester
		Years years = (Years) session.get(Years.class, id);
		
		// Delete 
		session.delete(years);
	}
	
		
	
	/**
	 * Edits an existing student semester
	 */
	public void edit(Years years) {
		logger.debug("Editing existing StudentLines");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers via id
		Years existingYear = (Years) session.get(Years.class, years.getId());
		
		// Assign updated values to this student volunteers
		existingYear.setYr(years.getYr());

		// Save updates
		session.save(existingYear);
	}

}
