package tn.esprit.spring.control;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Major;

import tn.esprit.spring.repository.MajorRepository;

@Controller
@RestController
@RequestMapping(value = "/major")
public class MajorController {

	
	@Autowired
	MajorRepository majRep;
	
	
	

	@PostMapping(value ="/createmajor")
	public Major CreateMajor(@Valid @RequestBody Major maj)
	{
		
		return majRep.save(maj) ;
	}
	
}
