package my.photoalbum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	@GetMapping()
	public String index() {
		return "/guest/index.html";
	}
	
	@GetMapping("/guest")
	public String indexGuest() {
		return "/guest/index.html";
	}
	
	@GetMapping("/guest/show")
	public String showPhoto() {

		return "/guest/show.html";

	}
	
	@PostMapping("/guest/show")
	public String createComment() {

		return "/guest/show.html";

	}
	
}
