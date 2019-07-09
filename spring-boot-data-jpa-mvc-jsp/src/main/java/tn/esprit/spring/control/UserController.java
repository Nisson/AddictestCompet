package tn.esprit.spring.control;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.ResourceNotFoundException;
import tn.esprit.spring.service.UserService;

@Controller
@RestController
@RequestMapping(value = "/user")

public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	
	

	 
	
	//create user 
	@PostMapping(value ="/createuser")
	public User createuser(@Valid @RequestBody User user) {
	return userRepository.save(user);
	}
	 //show
		@GetMapping(value = "/show")

		public ResponseEntity<List<User>> getAll()
		{
			 return new ResponseEntity<List<User>>(userService.showAllUsers(), HttpStatus.OK);
			
		}
	//update 
	@PutMapping(value ="/update/{id}")
	public User updateNote(@PathVariable(value = "id") Long userId,
	                                        @Valid @RequestBody User noteDetails) {

	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

	    user.setNom((noteDetails.getNom()));
	    user.setPrenom(noteDetails.getPrenom());
	    user.setEmail((noteDetails.getEmail()));
	    user.setPassword((noteDetails.getPassword()));
	    user.setUsername((noteDetails.getUsername()));
	    

	    User updatedNote = userRepository.save(user);
	    return updatedNote;
	}
	
	//delete 
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity <?> delete(@PathVariable(value="id")Long id)
{
		userRepository.deleteById(id);
	return  ResponseEntity.ok().build();
	
}

}
