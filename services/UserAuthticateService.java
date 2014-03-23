package org.celts.db.service;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.celts.db.domain.UserLogin;

public class UserAuthticateService {
	
    private HibernateTemplate hibernateTemplate;
    
    public UserAuthticateService() {
            // TODO Auto-generated constructor stub
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
            this.hibernateTemplate = hibernateTemplate;
    }
    
    public boolean verifyUserNameAndPassword(String uname,String pass) {
            System.out.println("Inside into service class");
            boolean userStatus = false;
            try {
                    @SuppressWarnings("unchecked")
                    List<UserLogin> userObjs = hibernateTemplate.find("from UserLogin u where u.uname=? and u.pass=?",uname,pass);
                    if(userObjs.size() != 0) {
                            userStatus = true;
                    }
            } catch(Exception e) {
                    e.printStackTrace();
            }
            return userStatus;
    }

}
