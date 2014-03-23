package org.celts.db.service;

import java.util.List;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.CommunityServiceProgramsLines;
import org.celts.db.domain.CommunityPartnerOrganizations;
import org.celts.db.domain.CommunityParticipants;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("communityServiceProgramsLinesService")
@Transactional
public class CommunityServiceProgramsLinesService {
	
protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all student volunteers
	 */
	@SuppressWarnings("unchecked")
	public List<CommunityServiceProgramsLines> getAllCmmPntnrOrgs(Integer CmmPntnrOrgsId) {
		logger.debug("Retrieving all student volunteers");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("SELECT e.id, cast(group_concat(cast(concat(' ', e.sempartnered, ' ', e.yrpartnered) as string)) as string), cast(group_concat(cast(concat(' ', e.cmmsrvprog)as string)) as string) FROM CommunityServiceProgramsLines e WHERE e.communityPartnerOrganizations.id=" +CmmPntnrOrgsId+ "group by " +
				"e.communityPartnerOrganizations.id");
		
		//Query query = session.createQuery("SELECT e.studentVolunteer.id, e.id, e.sem, studentVolunteer.year_volunteered FROM StudentLines e WHERE e.studentVolunteer.id=" +studentVolunteerId+ "group by e.studentVolunteer.id");	
		return  query.list();
	}
	
	
	
	/**
	 * Retrieves all student volunteers
	 */
	@SuppressWarnings("unchecked")
	public List<CommunityServiceProgramsLines> getAllCmmPtcp(Integer CmmPtcpId) {
		logger.debug("Retrieving all student volunteers");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("SELECT e.id, cast(group_concat(cast(concat(' ', e.semptcpd, ' ', e.yrptcpd) as string)) as string), cast(group_concat(cast(concat(' ', e.cmmsrvprog)as string)) as string) FROM CommunityServiceProgramsLines e WHERE e.communityParticipants.id=" +CmmPtcpId+ "group by " +
				"e.communityParticipants.id");
	
		
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Retrieves a single student volunteers
	 */
	public CommunityServiceProgramsLines get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers
		CommunityServiceProgramsLines communityServiceProgramsLines = (CommunityServiceProgramsLines) session.get(CommunityServiceProgramsLines.class, id);
		
		// Persists to db
		return communityServiceProgramsLines;
	}
	
	/**
	 * Adds a new student volunteers
	 */
	public void add(Integer CommunityPartnerOrganizationsId, CommunityServiceProgramsLines communityServiceProgramsLines) {
		logger.debug("Adding new student volunteers");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing StudentVolunteer via id
		CommunityPartnerOrganizations existingCommunityPartnerOrganizations = (CommunityPartnerOrganizations) session.get(CommunityPartnerOrganizations.class, CommunityPartnerOrganizationsId);
		
		// Add StudentVolunteer to student volunteers
		communityServiceProgramsLines.setCommunityPartnerOrganizations(existingCommunityPartnerOrganizations);
		
		// Persists to db
		session.save(communityServiceProgramsLines);
	}
	
	
	
	/**
	 * Adds a new student volunteers
	 */
	public void addPtcp(Integer communityParticipantId, CommunityServiceProgramsLines communityServiceProgramsLines) {
		logger.debug("Adding new student volunteers");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing StudentVolunteer via id
		CommunityParticipants existingCommunityParticipants = (CommunityParticipants) session.get(CommunityParticipants.class, communityParticipantId);
		
		// Add StudentVolunteer to student volunteers
		communityServiceProgramsLines.setCommunityParticipants(existingCommunityParticipants);
		
		// Persists to db
		session.save(communityServiceProgramsLines);
	}
	
	
	/**
	 * Deletes an existing student volunteers
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing student volunteers");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers
		CommunityServiceProgramsLines communityServiceProgramsLines = (CommunityServiceProgramsLines) session.get(CommunityServiceProgramsLines.class, id);
		
		// Delete 
		session.delete(communityServiceProgramsLines);
	}
	
		
	
	/**
	 * Deletes all student volunteers based on the StudentVolunteer's id
	 */
	public void deleteAll(Integer cmmSrvcProgId) {
		logger.debug("Deleting existing student volunteers based on StudentVolunteer's id");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("DELETE FROM CommunityServiceProgramsLines WHERE communityPartnerOrganizations.id=" +cmmSrvcProgId);
		
		// Delete all
		query.executeUpdate();
	}
	
	/**
	 * Edits an existing student volunteers
	 */
	public void editCmm(CommunityServiceProgramsLines communityServiceProgramsLines) {
		logger.debug("Editing existing StudentLines");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers via id
		CommunityServiceProgramsLines existingCommunityServiceProgramsLines = (CommunityServiceProgramsLines) session.get(CommunityServiceProgramsLines.class, communityServiceProgramsLines.getId());
		
		// Assign updated values to this student volunteers
		existingCommunityServiceProgramsLines.setSempartnered(communityServiceProgramsLines.getSempartnered());
		existingCommunityServiceProgramsLines.setYrpartnered(communityServiceProgramsLines.getYrpartnered());
		existingCommunityServiceProgramsLines.setCmmsrvprog(communityServiceProgramsLines.getCmmsrvprog());
		//existingCommunityServiceProgramsLines.setSemptcpd(communityServiceProgramsLines.getSemptcpd());
		//existingCommunityServiceProgramsLines.setYrptcpd(communityServiceProgramsLines.getYrptcpd());
		
		// Save updates
		session.save(existingCommunityServiceProgramsLines);
	}
	
	
	
	/**
	 * Edits an existing student volunteers
	 */
	public void editCmmPctp(CommunityServiceProgramsLines communityServiceProgramsLines) {
		logger.debug("Editing existing StudentLines");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing student volunteers via id
		CommunityServiceProgramsLines existingCommunityServiceProgramsLines = (CommunityServiceProgramsLines) session.get(CommunityServiceProgramsLines.class, communityServiceProgramsLines.getId());
		
		// Assign updated values to this student volunteers
		existingCommunityServiceProgramsLines.setCmmsrvprog(communityServiceProgramsLines.getCmmsrvprog());
		existingCommunityServiceProgramsLines.setSemptcpd(communityServiceProgramsLines.getSemptcpd());
		existingCommunityServiceProgramsLines.setYrptcpd(communityServiceProgramsLines.getYrptcpd());
		
		// Save updates
		session.save(existingCommunityServiceProgramsLines);
	}
	
}
