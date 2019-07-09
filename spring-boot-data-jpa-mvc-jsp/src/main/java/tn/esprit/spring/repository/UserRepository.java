package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	 
	List<User> findByFName(String fname);
	List<User> findByLName(String lname);
	
//	@Query("SELECT (MAX(e.id), 0) FROM User e") 
//	Long getMaxId(); 
	
}
