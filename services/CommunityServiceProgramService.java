package org.celts.db.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.CommunityServiceProgram;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("communityServiceProgramService")
@Transactional
public class CommunityServiceProgramService {
	
	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Retrieves all student volunteers
	 */
	@SuppressWarnings("unchecked")
	public List<CommunityServiceProgram> getAll() {
		logger.debug("Retrieving all semesters");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  CommunityServiceProgram");
		
		
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Retrieves a single student semester
	 */
	public CommunityServiceProgram get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers
		CommunityServiceProgram communityServiceProgram = (CommunityServiceProgram) session.get(CommunityServiceProgram.class, id);
		
		// Persists to db
		return communityServiceProgram;
	}
	
	
	/**
	 * Adds a new semester
	 */
	public void add(CommunityServiceProgram communityServiceProgram) {
		logger.debug("Adding new semester");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Persists to db
		session.save(communityServiceProgram);
	}
	
	/**
	 * Deletes an existing semester
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing student semester");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student semester
		CommunityServiceProgram communityServiceProgram = (CommunityServiceProgram) session.get(CommunityServiceProgram.class, id);
		
		// Delete 
		session.delete(communityServiceProgram);
	}
	
		
	
	/**
	 * Edits an existing student semester
	 */
	public void edit(CommunityServiceProgram communityServiceProgram) {
		logger.debug("Editing existing StudentLines");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers via id
		CommunityServiceProgram existingCommunityServiceProgram = (CommunityServiceProgram) session.get(CommunityServiceProgram.class, communityServiceProgram.getId());
		
		// Assign updated values to this student volunteers
		existingCommunityServiceProgram.setProg(communityServiceProgram.getProg());

		// Save updates
		session.save(existingCommunityServiceProgram);
	}

}
