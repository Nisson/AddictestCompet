package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Question implements Serializable {
	
	
	private Long id; 
	
	private String content;
	private Boolean answer1;
	
	private Long affected_major;
	private List<Long> major_group;
	
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Question(Long id, String content, Boolean answer1, Long affected_major) {
		super();
		this.id = id;
		this.content = content;
		this.answer1 = answer1;
		
		this.affected_major = affected_major;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getAnswer1() {
		return answer1;
	}
	public void setAnswer1(Boolean answer1) {
		this.answer1 = answer1;
	}
	
	public Long getAffected_major() {
		return affected_major;
	}
	public void setAffected_major(Long affected_major) {
		this.affected_major = affected_major;
	}
	public List<Long> getMajor_group() {
		return major_group;
	}
	public void setMajor_group(List<Long> major_group) {
		this.major_group = major_group;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
