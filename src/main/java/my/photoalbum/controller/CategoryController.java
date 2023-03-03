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

import my.photoalbum.model.Category;
import my.photoalbum.repository.CategoryRepository;
import my.photoalbum.repository.PhotoRepository;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	PhotoRepository PhotoRepository;

	@Autowired
	CategoryRepository CategoryRepository;

	@GetMapping()
	public String index(Model model) {
		List<Category> categoryList = CategoryRepository.findAll();
		model.addAttribute("elencoCategorie", categoryList);

		return "categories/index";
	}
	
	@GetMapping("/create")		//gestirà le richieste GET di tipo /category/create
	public String create(Model model) {
		Category category=new Category();	//non esiste ancora sul DB
		
		model.addAttribute("category", category);
	
		return "categories/create";
	}

	@PostMapping("/create")
	public String store(
		@ModelAttribute("category") Category formCategory, 
		BindingResult bindingResult,
		Model model){
	
//		if (bindingResult.hasErrors())
//			return "Categories/index";
		
		CategoryRepository.save(formCategory);
		
		return "redirect:/categories";
		
	}
	
}
