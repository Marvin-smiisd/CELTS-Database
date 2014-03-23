package org.celts.db.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.CommunityParticipants;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("communityParticipantService")
@Transactional
public class CommunityParticipantService {
	
	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Retrieves all student volunteers
	 */
	@SuppressWarnings("unchecked")
	public List<CommunityParticipants> getAll() {
		logger.debug("Retrieving all community participanys");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  CommunityParticipants");
		
		
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Retrieves a single student semester
	 */
	public CommunityParticipants get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers
		CommunityParticipants communityParticipants = (CommunityParticipants) session.get(CommunityParticipants.class, id);
		
		// Persists to db
		return communityParticipants;
	}
	
	
	/**
	 * Adds a new semester
	 */
	public void add(CommunityParticipants communityParticipants) {
		logger.debug("Adding new semester");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Persists to db
		session.save(communityParticipants);
	}
	
	/**
	 * Deletes an existing semester
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing student semester");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student semester
		CommunityParticipants communityParticipants = (CommunityParticipants) session.get(CommunityParticipants.class, id);
		
		// Delete 
		session.delete(communityParticipants);
	}
	
		
	
	/**
	 * Edits an existing student semester
	 */
	public void edit(CommunityParticipants communityParticipants) {
		logger.debug("Editing existing StudentLines");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers via id
		CommunityParticipants existingCommunityParticipants = (CommunityParticipants) session.get(CommunityParticipants.class, communityParticipants.getId());
		
		// Assign updated values to this student volunteers
		//existingCommunityParticipants.setId(communityParticipants.getId());
		existingCommunityParticipants.setFirstName(communityParticipants.getFirstName());
		existingCommunityParticipants.setLastName(communityParticipants.getLastName());
	

		// Save updates
		session.save(existingCommunityParticipants);
	}
}
