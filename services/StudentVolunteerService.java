package org.celts.db.service;

import java.util.List;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
//import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.celts.db.domain.StudentVolunteer;
//import org.celts.db.domain.Years;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for processing StudentVolunteers
 * 
 * @author
 */
@Service("studentVolunteerService")
@Transactional
public class StudentVolunteerService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all StudentVolunteers
	 * 
	 * @return a list of StudentVolunteers
	 */
	@SuppressWarnings("unchecked")
	public List<StudentVolunteer> getAll() {
		logger.debug("Retrieving all StudentVolunteers");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  StudentVolunteer");
		
		// Retrieve all
		return  query.list();
	}
		
	
	/**
	 * Retrieves all StudentVolunteers
	 * 
	 * @return a list of StudentVolunteers
	 */

	@SuppressWarnings("unchecked")
	public List<StudentVolunteer> getAllByYear() {
		logger.debug("Retrieving all StudentVolunteers");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Query query = session.createQuery("SELECT stu.id, stu.num, stu.firstName, stu.lastName FROM  StudentVolunteer stu");//, Years yrs" +
				//" where stu.id = yrs.studentVolunteer.id");
		// Retrieve all
		//return  query.list();
			
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  StudentVolunteer");
		
		//Query query = session.createQuery("FROM  StudentVolunteer stu, Years y WHERE y.studentVolunteer.id = stu.id and y.yr = '2013'");
		
		return  query.list();
		//Query query = session.createQuery("FROM  StudentVolunteer e where e.id = ");
	}	
	

	/**
	 * Retrieves a single StudentVolunteer
	 */
	public StudentVolunteer get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing StudentVolunteer
		StudentVolunteer StudentVolunteer = (StudentVolunteer) session.get(StudentVolunteer.class, id);
		
		return StudentVolunteer;
	}
	
	/**
	 * Adds a new StudentVolunteer
	 */
	public void add(StudentVolunteer StudentVolunteer) {
		logger.debug("Adding new StudentVolunteer");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Persists to db
		session.save(StudentVolunteer);
	}
	
	/**
	 * Deletes an existing StudentVolunteer
	 * @param id the id of the existing StudentVolunteer
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing StudentVolunteer");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing StudentVolunteer
		StudentVolunteer StudentVolunteer = (StudentVolunteer) session.get(StudentVolunteer.class, id);
		
		// Delete 
		session.delete(StudentVolunteer);
	}
	
	/**
	 * Edits an existing StudentVolunteer
	 */
	public void edit(StudentVolunteer studentVolunteer) {
		logger.debug("Editing existing StudentVolunteer");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing StudentVolunteer via id
		StudentVolunteer existingStudentVolunteer = (StudentVolunteer) session.get(StudentVolunteer.class, studentVolunteer.getId());
		
		// Assign updated values to this StudentVolunteer
		existingStudentVolunteer.setNum(studentVolunteer.getNum());
		existingStudentVolunteer.setFirstName(studentVolunteer.getFirstName());
		existingStudentVolunteer.setLastName(studentVolunteer.getLastName());
		//existingStudentVolunteer.setYear(studentVolunteer.getYear());

		// Save updates
		session.save(existingStudentVolunteer);
	}
}
