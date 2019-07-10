package blog.article1;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import tn.esprit.spring.entity.Major;

public class WebScraper2 {

	public static void main(String[] args) {
		
	    
		String baseUrl = "https://www.niche.com/colleges/search/best-colleges-for-international-relations/" ;
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
	System.out.println(items);
	HtmlElement itemMajor = ((HtmlElement) items.get(0).getFirstByXPath(".//div[@class='search-result-badge']"));
	System.out.println(itemMajor.asText());
				for(HtmlElement htmlItem : items){
					HtmlElement itemAnchor = ((HtmlElement) htmlItem.getFirstByXPath(".//h2[@class='search-result__title']"));
					HtmlAnchor itemUrl = ((HtmlAnchor) htmlItem.getFirstByXPath(".//a[@class='search-result__link']"));
					
					//Major major = new Major();
					//major.setTitle(itemAnchor.asText());
					//System.out.println(major);
					
					System.out.println(itemAnchor.asText());
				
					
					ObjectMapper mapper = new ObjectMapper();
					// jsonString = mapper.writeValueAsString(major) ;
					
					//System.out.println(jsonString);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}

	}

}
