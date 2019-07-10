package tn.esprit.spring.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Major;
import tn.esprit.spring.repository.MajorRepository;

@Service
public class MajorService {

	@Autowired
	MajorRepository majRep;
	
	public Major CreateMajor( Major maj)
	{
		
		return majRep.save(maj) ;
	}
	
}
