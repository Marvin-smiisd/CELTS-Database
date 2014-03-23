package org.celts.db.dao;

import java.util.List;

import org.celts.db.domain.StudentLines;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class StudentLinesDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public StudentLines getById(int id)
	{
		return (StudentLines) sessionFactory.getCurrentSession().get(StudentLines.class, id);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<StudentLines> searchSemAndProg(String prog)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentLines.class);
		criteria.add(Restrictions.ilike("prog", prog+"%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<StudentLines> getAllSemAndProg()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentLines.class);
		return criteria.list();
	}
	
	public int save(StudentLines studentLines)
	{
		return (Integer) sessionFactory.getCurrentSession().save(studentLines);
	}
	
	public void update(StudentLines studentLines)
	{
		sessionFactory.getCurrentSession().merge(studentLines);
	}
	
	public void delete(int id)
	{
		StudentLines studentLines = getById(id);
		sessionFactory.getCurrentSession().delete(studentLines);
	}
}
