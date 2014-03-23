package org.celts.db.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.CommunityPartnerOrganizations;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("communityPartnerOrganizationsService")
@Transactional
public class CommunityPartnerOrganizationsService {

protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Retrieves all student volunteers
	 */
	@SuppressWarnings("unchecked")
	public List<CommunityPartnerOrganizations> getAll() {
		logger.debug("Retrieving all semesters");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  CommunityPartnerOrganizations");
		
		
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Retrieves a single student semester
	 */
	public CommunityPartnerOrganizations get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers
		CommunityPartnerOrganizations communityPartnerOrganizations = (CommunityPartnerOrganizations) session.get(CommunityPartnerOrganizations.class, id);
		
		// Persists to db
		return communityPartnerOrganizations;
	}
	
	
	/**
	 * Adds a new semester
	 */
	public void add(CommunityPartnerOrganizations communityPartnerOrganizations) {
		logger.debug("Adding new semester");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Persists to db
		session.save(communityPartnerOrganizations);
	}
	
	/**
	 * Deletes an existing semester
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing student semester");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student semester
		CommunityPartnerOrganizations communityPartnerOrganizations = (CommunityPartnerOrganizations) session.get(CommunityPartnerOrganizations.class, id);
		
		// Delete 
		session.delete(communityPartnerOrganizations);
	}
	
		
	
	/**
	 * Edits an existing student semester
	 */
	public void edit(CommunityPartnerOrganizations communityPartnerOrganizations) {
		logger.debug("Editing existing StudentLines");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers via id
		CommunityPartnerOrganizations existingCommunityPartnerOrganizations = (CommunityPartnerOrganizations) session.get(CommunityPartnerOrganizations.class, communityPartnerOrganizations.getId());
		
		// Assign updated values to this student volunteers
		//existingCommunityPartnerOrganizations.setId(communityPartnerOrganizations.getId());
		existingCommunityPartnerOrganizations.setOrg(communityPartnerOrganizations.getOrg());
		existingCommunityPartnerOrganizations.setContact(communityPartnerOrganizations.getContact());

		// Save updates
		session.save(existingCommunityPartnerOrganizations);
	}
}
