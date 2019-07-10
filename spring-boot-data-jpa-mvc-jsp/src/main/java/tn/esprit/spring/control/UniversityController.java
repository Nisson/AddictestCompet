package tn.esprit.spring.control;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.University;
import tn.esprit.spring.repository.UniversityRepository;

@Controller
@RestController
@RequestMapping(value = "/university")
public class UniversityController {

	@Autowired
	UniversityRepository univRep;
	
	@PostMapping(value ="/createuni")
	public University CreateUniversity(@Valid @RequestBody University uni)
	{
		
		return univRep.save(uni);
	}
	
}
