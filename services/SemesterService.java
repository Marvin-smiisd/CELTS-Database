package org.celts.db.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.Semesters;
//import org.celts.db.domain.StudentVolunteer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("semesterService")
@Transactional
public class SemesterService {
	
	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Retrieves all student volunteers
	 */
	@SuppressWarnings("unchecked")
	public List<Semesters> getAll() {
		logger.debug("Retrieving all semesters");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Semesters");
		
		
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Retrieves a single student semester
	 */
	public Semesters get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers
		Semesters semesters = (Semesters) session.get(Semesters.class, id);
		
		// Persists to db
		return semesters;
	}
	
	
	/**
	 * Adds a new semester
	 */
	public void add(Semesters semester) {
		logger.debug("Adding new semester");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Persists to db
		session.save(semester);
	}
	
	/**
	 * Deletes an existing semester
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing student semester");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student semester
		Semesters semesters = (Semesters) session.get(Semesters.class, id);
		
		// Delete 
		session.delete(semesters);
	}
	
		
	
	/**
	 * Edits an existing student semester
	 */
	public void edit(Semesters semester) {
		logger.debug("Editing existing StudentLines");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers via id
		Semesters existingSemester = (Semesters) session.get(Semesters.class, semester.getId());
		
		// Assign updated values to this student volunteers
		existingSemester.setSem(semester.getSem());

		// Save updates
		session.save(existingSemester);
	}

}
