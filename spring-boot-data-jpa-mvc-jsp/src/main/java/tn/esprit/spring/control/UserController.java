package tn.esprit.spring.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	
	@GetMapping(value = "/show")

	public ResponseEntity<List<User>> getAll()
	{
		 return new ResponseEntity<List<User>>(userService.showAllUsers(), HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity <?> delete(@PathVariable(value="id")Long id)
{
		userRepository.deleteById(id);
	return  ResponseEntity.ok().build();
	
}

}
