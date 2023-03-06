package my.photoalbum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import my.photoalbum.model.Comment;
import my.photoalbum.repository.CategoryRepository;
import my.photoalbum.repository.CommentRepository;
import my.photoalbum.repository.PhotoRepository;

@Controller
@RequestMapping("/guest")
public class CommentController {

	@Autowired
	PhotoRepository PhotoRepository;

	@Autowired
	CategoryRepository CategoryRepository;
	
	@Autowired
	CommentRepository CommentRepository;

	@GetMapping("/comments")
	public String index(Model model) {
		List<Comment> CommentList = CommentRepository.findAll();
		model.addAttribute("elencoCategorie", CommentList);

		return "comments";
	}
	
	@GetMapping("/comments/create")
	public String create(Model model) {
		Comment Comment=new Comment();	//non esiste ancora sul DB
		
		model.addAttribute("Comment", Comment);
	
		return "guest/comments/create";
	}

	@PostMapping("/comments/create")
	public String store(
		@ModelAttribute("Comment") Comment formComment, 
		BindingResult bindingResult,
		Model model){
		
		CommentRepository.save(formComment);
		
		return "redirect:/guest";
		
	}	
	
	
}
