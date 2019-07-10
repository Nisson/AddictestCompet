package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.University;
import tn.esprit.spring.repository.UniversityRepository;

@Service
public class UniversityService {

	@Autowired
	UniversityRepository univRep;
	
	
	public University CreateUniversity( University uni)
	{
		
		return univRep.save(uni);
	}
}
