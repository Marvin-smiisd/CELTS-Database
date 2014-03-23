package org.celts.db.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.BonnerScholars;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("bonnerScholarsService")
@Transactional
public class BonnerScholarsService {
	
protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all student volunteers
	 */
	@SuppressWarnings("unchecked")
	public List<BonnerScholars> getAll() {
		logger.debug("Retrieving all student volunteers");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM BonnerScholars");
		
		return  query.list();
	}
	
	

	/**
	 * Retrieves a single StudentVolunteer
	 */
	public BonnerScholars get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing StudentVolunteer
		BonnerScholars bonnerScholars = (BonnerScholars) session.get(BonnerScholars.class, id);
		
		return bonnerScholars;
	}
	
	/**
	 * Adds a new StudentVolunteer
	 */
	public void add(BonnerScholars bonnerScholars) {
		logger.debug("Adding new StudentVolunteer");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Persists to db
		session.save(bonnerScholars);
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
		BonnerScholars bonnerScholars = (BonnerScholars) session.get(BonnerScholars.class, id);
		
		// Delete 
		session.delete(bonnerScholars);
	}
	
	/**
	 * Edits an existing StudentVolunteer
	 */
	public void edit(BonnerScholars bonnerScholars) {
		logger.debug("Editing existing StudentVolunteer");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing StudentVolunteer via id
		BonnerScholars existingBonnerScholars = (BonnerScholars) session.get(BonnerScholars.class, bonnerScholars.getId());
		
		// Assign updated values to this StudentVolunteer
		existingBonnerScholars.setFirstName(bonnerScholars.getFirstName());
		existingBonnerScholars.setLastName(bonnerScholars.getLastName());
		//existingBonnerScholars.setSemofentry(bonnerScholars.getSemofentry());
		//existingBonnerScholars.setSemofdeprtr(existingBonnerScholars.getSemofdeprtr());

		// Save updates
		session.save(existingBonnerScholars);
	}

}
