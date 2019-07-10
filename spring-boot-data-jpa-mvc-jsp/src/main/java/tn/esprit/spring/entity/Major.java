package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ManyToAny;



@Entity
public class Major implements Serializable {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id; 
	private String title;
	
	@ManyToMany(mappedBy="majors")
	private List<University> universities;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Major [id=" + id + ", title=" + title + "]";
	}
	public List<University> getUniversities() {
		return universities;
	}
	public void setUniversities(List<University> universities) {
		this.universities = universities;
	}
	

}
