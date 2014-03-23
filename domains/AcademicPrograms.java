package org.celts.db.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AcademicPrograms")
public class AcademicPrograms implements Serializable {
		
		private static final long serialVersionUID = 5924361831551833717L;
		
		@Id
		@Column(name = "id")
		@GeneratedValue
		private Integer id;

		@Column(name = "Academic_Program_Name")
		private String academicprog;
		
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		
		public String getAcademicprog() {
			return academicprog;
		}
		public void setAcademicprog(String academicprog) {
			this.academicprog = academicprog;
		}

}
