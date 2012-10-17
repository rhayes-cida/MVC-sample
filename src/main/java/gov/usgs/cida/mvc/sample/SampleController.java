package gov.usgs.cida.mvc.sample;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Sample Spring MVC controller class
 * 
 * @author rhayes
 * @see http://static.springsource.org/spring/docs/current/spring-framework-reference/html/mvc.html
 */
@org.springframework.stereotype.Controller
public class SampleController {

	public static class SimpleObject {
		private String family;
		private String given;
		
		public SimpleObject(String tag) {
			this.family = tag;
		}
		
		public String getFamily() {
			return family;
		}

		public String getGiven() {
			return given;
		}

		public void setGiven(String given) {
			this.given = given;
		}
		
	}
	
	// return view name, after adding to implicit model
	@RequestMapping("hello")
	public String index(Model model) {
		model.addAttribute("beverage", "coffee");
		return "hello";
	}
	
	// write the response yourself
	@RequestMapping(value="one", produces="text/plain")
	public void one(HttpServletResponse response) throws IOException {
		response.setContentType("text/plain");
		Writer w = response.getWriter();
		try {
			w.write("You got to one!\n");
		} finally {
			w.close();
		}
	}
	
	// Return an object that will get JSON-ified
	@RequestMapping("two/{pathvar}")
	@ResponseBody
	public SimpleObject two(
			@PathVariable String pathvar,
			@RequestParam String given
			)
	{
		SimpleObject s = new SimpleObject(pathvar);
		s.setGiven(given);
		return s;
	}
}
