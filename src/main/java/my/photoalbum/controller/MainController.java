package my.photoalbum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guest")
public class MainController {

	@GetMapping()
	public String index() {
		return "/guest/index.html";
	}
	
}
