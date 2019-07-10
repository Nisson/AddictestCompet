package tn.esprit.spring.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import tn.esprit.spring.entity.Major;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.entity.University;
import tn.esprit.spring.repository.MajorRepository;
import tn.esprit.spring.repository.UniversityRepository;
import tn.esprit.spring.service.MajorService;

@Controller
@RestController
@RequestMapping(value = "/major")
public class MajorController {

	
	@Autowired
	MajorRepository majRep;
	@Autowired
	UniversityRepository univRep;
	
	static List<Question> questions= new ArrayList();
	static List<Long> ok= new ArrayList();
	@PostMapping(value ="/createmajor")
	public Major CreateMajor(@Valid @RequestBody Major maj)
	{
		
		return majRep.save(maj) ;
	}
	
	@PostMapping(value ="/charge")
public void chargeBase() {
		
	    
		String baseUrl = "https://www.niche.com/colleges/american-university/rankings/" ;
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {
			String searchUrl = baseUrl ;
			HtmlPage page = client.getPage(searchUrl);
			
			List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//li[@class='rankings-expansion__badge']") ;
			if(items.isEmpty()){
				System.out.println("No items found !");
			}else{
	
				for(HtmlElement htmlItem : items){
					HtmlElement itemAnchor = ((HtmlElement) htmlItem.getFirstByXPath(".//div[@class='rankings-card__link__title']"));
					HtmlAnchor itemUrl = ((HtmlAnchor) htmlItem.getFirstByXPath(".//a[@class='rankings-card__link']"));
					
					getUniversityByMajor(itemUrl.getHrefAttribute());
				
					ObjectMapper mapper = new ObjectMapper();
					
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}

	}
	 public void getUniversityByMajor(String url) {
		 
	    
		String baseUrl = url ;
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {
			String searchUrl = baseUrl ;
			HtmlPage page = client.getPage(searchUrl);
			
			List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//div[@class='card']") ;
			if(items.isEmpty()){
				System.out.println("No items found !");
			}else{
				HtmlElement itemMajor = ((HtmlElement) items.get(0).getFirstByXPath(".//div[@class='search-result-badge']"));
				
				University university= new University();
				Major major = new Major();
				
				if(majorNale(itemMajor.asText()).contains("Hardest")||majorNale(itemMajor.asText()).contains("Best")||majorNale(itemMajor.asText()).contains("Top")||majorNale(itemMajor.asText()).contains("Most"))
				{
					System.out.println("CC");
				}else {
				major.setUniversities(new ArrayList<>());
		
				major.setTitle(majorNale(itemMajor.asText()));
				majRep.save(major);
				
				for(HtmlElement htmlItem : items){
					
					HtmlElement itemTitle  = ((HtmlElement) htmlItem.getFirstByXPath(".//h2[@class='search-result__title']"));
					HtmlAnchor itemUrl = ((HtmlAnchor) htmlItem.getFirstByXPath(".//a[@class='search-result__link']"));
				
					university = new University();
					university.setTitle(itemTitle.asText());
					if(univRep.findAll().contains(university))
					{
						university.getMajors().add(major);
						univRep.save(university);
					}else 
					{	//save university
						university.getMajors().add(major);
						univRep.save(university);						
					}
				}
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}

	}
public static String majorNale(String ch)
{
	
    int i =ch.indexOf("for", 0);
    String ne = ch.substring(i+4,ch.length());
    int i2 =ne.indexOf(" in",0) ;
    String toremove = ne.substring(i2, ne.length());
    
    return ne.replace(toremove , "");
}

@PostMapping(value ="/chargeUniv")
	
public void chargeUniversite() {
	

	
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		String title="";
		try {
			for(University u : univRep.findAll()) {
				title=u.getTitle().replace(" ", "-");
				title=title.replace("&", "-and-");
				title=title.replace("'", "");
				title=title.replace(".", "");
			try {
				
			
			String searchUrl = "https://www.niche.com/colleges/"+title ;
			HtmlPage page = client.getPage(searchUrl);
			
			List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//section[@class='block--two-two']") ;
			if(items.isEmpty()){
				System.out.println("No items found !");
			}else{
				
					HtmlElement itemTitle  = ((HtmlElement) items.get(0).getFirstByXPath(".//div[@class='profile__bucket--3']"))
							.getFirstByXPath(".//div[@class='scalar__value']");
					if(itemTitle.equals(null))
						itemTitle  = ((HtmlElement) items.get(0).getFirstByXPath(".//div[@class='profile__bucket--4']"))
						.getFirstByXPath(".//div[@class='scalar__value']");
					u.setScore(itemTitle.asText());

					HtmlElement itemDeadline  = ((HtmlElement) items.get(0).getFirstByXPath(".//div[@class='profile__bucket--2']"))
							.getFirstByXPath(".//div[@class='scalar__value']");
					u.setDeadline(itemDeadline.asText());
					univRep.save(u);
					
				
					
				
			}}
			 catch (java.lang.NullPointerException e) {
				// TODO: handle exception
			}}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
@PostMapping(value ="/chargeDes")

public void chargeDes() {
	

	
	WebClient client = new WebClient();
	client.getOptions().setCssEnabled(false);
	client.getOptions().setJavaScriptEnabled(false);
	String title="";
	try {
		for(University u : univRep.findAll()) {
			title=u.getTitle().replace(" ", "-");
			title=title.replace("&", "-and-");
			title=title.replace("'", "");
			title=title.replace(".", "");
		try {
			
		
		String searchUrl = "https://www.niche.com/colleges/"+title ;
		HtmlPage page = client.getPage(searchUrl);
		
		List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//section[@class='block--one']") ;
		if(items.isEmpty()){
			System.out.println("No items found !");
		}else{
			
				HtmlElement itemTitle  = ((HtmlElement) items.get(0).getFirstByXPath(".//div[@class='profile__bucket--1']"))
					.getFirstByXPath(".//span[@class='bare-value']");
				
				u.setDescription(itemTitle.asText());
				univRep.save(u);
				
			
				
			
		}}
		 catch (java.lang.NullPointerException e) {
			// TODO: handle exception
		}}
	} catch(Exception e){
		e.printStackTrace();
	}
}
@PutMapping(value ="/delete")
public void clean_universities()
{
	List<University> list =univRep.findAll();
	List<University> toRemove =new ArrayList<>();
	for(University u : list)
	{
		try {u.getDeadline().equals(null);}catch(NullPointerException n) {univRep.delete(u);}
		try {u.getScore().equals(null);}catch(NullPointerException n) {univRep.delete(u);}
		
	}
	
	//univRep.deleteAll(toRemove);
}

@GetMapping(value ="/show")
public void shoz()
{
	List<Major> list =(List<Major>) majRep.findAll();
	
	for(Major u : list)
	{
		System.out.println(u);
		
	}
	
	
}

public void questionReponse()
{
	List<Major> G1 = new ArrayList<>();
	G1.add(majRep.getOne((long)5));
	G1.add(majRep.getOne((long)11));
	G1.add(majRep.getOne((long)6));
	
	List<Major> G2 = new ArrayList<>();
	G2.add(majRep.getOne((long)5));
	G2.add(majRep.getOne((long)1));
	G2.add(majRep.getOne((long)3));
	
	List<Major> G3 = new ArrayList<>();
	G3.add(majRep.getOne((long)10));
	G3.add(majRep.getOne((long)1));
	G3.add(majRep.getOne((long)9));
	
	List<Major> G4 = new ArrayList<>();
	G4.add(majRep.getOne((long)14));
	G4.add(majRep.getOne((long)8));
	G4.add(majRep.getOne((long)7));
	G4.add(majRep.getOne((long)12));
	
	List<Major> G5 = new ArrayList<>();
	List<Major> G6 = new ArrayList<>();
	List<Major> G7 = new ArrayList<>();
	List<Major> G8 = new ArrayList<>();
	
	G5.add(majRep.getOne((long)16));
	G5.add(majRep.getOne((long)4));
	G6.add(majRep.getOne((long)15));
	G7.add(majRep.getOne((long)2));
	G8.add(majRep.getOne((long)13));
	
	
}

@PostMapping("/push")
public ResponseEntity<Question> generateQuestion(@RequestBody Question q1)
{
	if(!q1.getAnswer1())
	{
		for(Long i:q1.getMajor_group())
			questions.remove(q1);
	}else ok.add(q1.getAffected_major());
	
	Question q = new Question();
Random rand = new Random();
q=questions.get(rand.nextInt(questions.size()));
questions.remove(q);
	return new ResponseEntity<Question>(q,HttpStatus.OK);
	}
 

}


	
	

