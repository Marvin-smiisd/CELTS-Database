package org.celts.db.service;

import java.util.List;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
//import org.hibernate.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.celts.db.domain.StudentLines;
import org.celts.db.domain.StudentVolunteer;
import org.celts.db.domain.BonnerScholars;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for processing student volunteers
 * 
 * @author Marvin Yormie
 */
@Service("studentLinesService")
@Transactional
public class StudentLinesService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all student volunteers
	 */
	@SuppressWarnings("unchecked")
	public List<StudentLines> getAll(Integer studentVolunteerId) {
		logger.debug("Retrieving all student volunteers");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		
		Query query = session.createQuery("SELECT e.studentVolunteer.id, e.id,  cast(group_concat(cast(concat(' ', e.sem, ' ', e.yr) as string)) as string),  cast(group_concat(cast(concat(' ', e.prog)as string)) as string)  FROM StudentLines e WHERE e.studentVolunteer.id=" +studentVolunteerId+ "group by e.studentVolunteer.id");
       
		return  query.list();
	}
	
	
	
	/**
	 * Retrieves all student volunteers
	 */
	@SuppressWarnings("unchecked")
	public List<StudentLines> getAllBonnerSchalars(Integer bnrScholarsId) {
		logger.debug("Retrieving all student volunteers");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)

		Query query = session.createQuery("SELECT e.bonnerScholars.id, e.id, cast(group_concat(cast(concat(' ', e.sementry, ' ', e.yr) as string)) as string), cast(group_concat(cast(concat(' ', e.semdeptr, ' ', e.yrofdeptr) as string)) as string), cast(group_concat(cast(concat(' ', e.prog)as string)) as string) FROM StudentLines e WHERE e.bonnerScholars.id=" +bnrScholarsId+ "group by e.bonnerScholars.id");
      	
		return  query.list();
	}
	
	

	/**
	 * Retrieves a single student volunteers
	 */
	public StudentLines get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers
		StudentLines StudentLines = (StudentLines) session.get(StudentLines.class, id);
		
		// Persists to db
		return StudentLines;
	}
	
	/**
	 * Adds a new student volunteers
	 */
	public void add(Integer studentVolunteerId, StudentLines studentLines) {
		logger.debug("Adding new student volunteers");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing StudentVolunteer via id
		StudentVolunteer existingStudentVolunteer = (StudentVolunteer) session.get(StudentVolunteer.class, studentVolunteerId);
		
		// Add StudentVolunteer to student volunteers
		studentLines.setStudentVolunteer(existingStudentVolunteer);
		
		// Persists to db
		session.save(studentLines);
	}
	
	
	
	/**
	 * Adds a new student volunteers
	 */
	public void addBnr(Integer bonnerScholarsId, StudentLines studentLines) {
		logger.debug("Adding new student volunteers");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing StudentVolunteer via id
		BonnerScholars existingBonnerScholars = (BonnerScholars) session.get(BonnerScholars.class, bonnerScholarsId);
		
		// Add StudentVolunteer to student volunteers
		studentLines.setBonnerScholars(existingBonnerScholars);
		
		// Persists to db
		session.save(studentLines);
	}
	

	
	/**
	 * Deletes an existing student volunteers
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing student volunteers");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers
		StudentLines studentLines = (StudentLines) session.get(StudentLines.class, id);
		
		// Delete 
		session.delete(studentLines);
	}
	
		
	
	/**
	 * Deletes all student volunteers based on the StudentVolunteer's id
	 */
	public void deleteAll(Integer studentVolunteerId) {
		logger.debug("Deleting existing student volunteers based on StudentVolunteer's id");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("DELETE FROM StudentLines WHERE studentVolunteer.id=" +studentVolunteerId);
		
		// Delete all
		query.executeUpdate();
	}
	
	
	/**
	 * Deletes all student volunteers based on the StudentVolunteer's id
	 */
	public void deleteAllBnrSchloars(Integer bnrScholarsId) {
		logger.debug("Deleting existing student volunteers based on StudentVolunteer's id");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("DELETE FROM StudentLines WHERE bonnerScholars.id=" +bnrScholarsId);
		
		// Delete all
		query.executeUpdate();
	}
	
	
	/**
	 * Edits an existing student volunteers
	 */
	public void edit(StudentLines studentLines) {
		logger.debug("Editing existing StudentLines");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers via id
		StudentLines existingStudentLines = (StudentLines) session.get(StudentLines.class, studentLines.getId());
		
		// Assign updated values to this student volunteers
		existingStudentLines.setSem(studentLines.getSem());
		existingStudentLines.setYr(studentLines.getYr());
		existingStudentLines.setYrofdeptr(studentLines.getYrofdeptr());
		existingStudentLines.setProg(studentLines.getProg());
		existingStudentLines.setSementry(studentLines.getSementry());
		existingStudentLines.setSemdeptr(studentLines.getSemdeptr());
		
		
		// Save updates
		session.save(existingStudentLines);
	}
}
