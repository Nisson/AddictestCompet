package tn.esprit.spring.service;

import tn.esprit.spring.entity.University;

public interface IUniversityService {

	 void CreateUniversity( University uni);
	
		
	 University findByUniversityByTitle(String title);
	
}
