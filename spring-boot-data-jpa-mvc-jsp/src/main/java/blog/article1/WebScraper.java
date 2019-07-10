package blog.article1;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import tn.esprit.spring.control.UniversityController;
import tn.esprit.spring.entity.Major;
import tn.esprit.spring.entity.University;
import tn.esprit.spring.service.UniversityService;


public class WebScraper {





	public static void main(String[] args) {
		
	    
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
					Major major = new Major();
					major.setTitle(itemAnchor.asText());
					
					System.out.println(itemUrl.getHrefAttribute());
					
					getUniversityByMajor(itemUrl.getHrefAttribute());
					
				
					
					ObjectMapper mapper = new ObjectMapper();
					String jsonString = mapper.writeValueAsString(major) ;
					
					System.out.println(jsonString);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}

	}
	 static void getUniversityByMajor(String url) {
		 
	    
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
				major.setUniversities(new ArrayList<>());
				major.setTitle(majorNale(itemMajor.asText()));
				
				//save
				
				if(university!=null)
				{
					//major.getUniversities().add(university);
				}else 
				{	//save university
					//major.getUniversities().add(university);
				}
				
				
				for(HtmlElement htmlItem : items){
					
					HtmlElement itemTitle = ((HtmlElement) htmlItem.getFirstByXPath(".//h2[@class='search-result__title']"));
					HtmlAnchor itemUrl = ((HtmlAnchor) htmlItem.getFirstByXPath(".//a[@class='search-result__link']"));
					System.out.println(itemUrl.getHrefAttribute());
					UniversityController.majorRep.save(major);
					if(university!=null)
					{
						university.setTitle(itemTitle.asText());
						major.getUniversities().add(university);
					}else 
					{	//save university
						major.getUniversities().add(university);
					}
					//Major major = new Major();
					//major.setTitle(itemAnchor.asText());
					//System.out.println(major);
					
					System.out.println(itemTitle.asText());
				
					
					ObjectMapper mapper = new ObjectMapper();
					// jsonString = mapper.writeValueAsString(major) ;
					
					//System.out.println(jsonString);
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

}
