package tn.esprit.spring.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.University;


public interface UniversityRepository extends CrudRepository<University, Long>,JpaRepository<University, Long>  {
	@Query("select j from University j where j.title = :title")
	public University findUniversityByTitle(@Param("title") String title);

}
