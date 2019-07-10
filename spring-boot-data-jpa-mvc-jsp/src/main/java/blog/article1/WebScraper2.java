package blog.article1;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import tn.esprit.spring.entity.Major;
import tn.esprit.spring.entity.University;

public class WebScraper2 {

	public static void main(String[] args) {
		
	    
		
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {
			String searchUrl = "https://www.niche.com/colleges/northeastern-university/" ;
			HtmlPage page = client.getPage(searchUrl);
			
			List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//section[@class='block--two-two']") ;
			if(items.isEmpty()){
				System.out.println("No items found !");
			}else{
				System.out.println("coucou");
				
				
				
					
					HtmlElement itemTitle  = ((HtmlElement) items.get(0).getFirstByXPath(".//div[@class='profile__bucket--3']"))
							.getFirstByXPath(".//div[@class='scalar__value']");
					System.out.println(itemTitle.asText());
				
					
				
			}
		} catch(Exception e){
			e.printStackTrace();
		}

	}

}
