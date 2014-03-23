package org.celts.db.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.celts.db.domain.StudentVolunteer;

/**
 * @author Marvin Yormie
 *
 */
@Repository
@Transactional
public class StudentVolunteerDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public StudentVolunteer getById(int id)
	{
		return (StudentVolunteer) sessionFactory.getCurrentSession().get(StudentVolunteer.class, id);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<StudentVolunteer> searchStudentVolunteers(String firstName)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentVolunteer.class);
		//Criteria criteri = sessionFactory.getCurrentSession().createCriteria(StudentVolunteer.class);
		criteria.add(Restrictions.ilike("firstName", firstName+"%"));
		//criteri.add(Restrictions.ilike("lastName", lastName+"%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<StudentVolunteer> getAllStudentVolunteers()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentVolunteer.class);
		return criteria.list();
	}
	
	public int save(StudentVolunteer studentVolunteer)
	{
		return (Integer) sessionFactory.getCurrentSession().save(studentVolunteer);
	}
	
	public void update(StudentVolunteer studentVolunteer)
	{
		sessionFactory.getCurrentSession().merge(studentVolunteer);
	}
	
	public void delete(int id)
	{
		StudentVolunteer s = getById(id);
		sessionFactory.getCurrentSession().delete(s);
	}

}
