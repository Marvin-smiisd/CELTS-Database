package org.celts.db.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BonnerScholars")
public class BonnerScholars implements Serializable {
		
		private static final long serialVersionUID = 5924361831551833717L;
		
		@Id
		@Column(name = "id")
		@GeneratedValue
		private Integer id;
		
		@Column(name = "FIRST_NAME")
		private String firstName;

		@Column(name = "LAST_NAME")
		private String lastName;
		
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
}
