package my.photoalbum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import my.photoalbum.model.Category;
import my.photoalbum.repository.CategoryRepository;
import my.photoalbum.repository.CommentRepository;
import my.photoalbum.repository.PhotoRepository;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	PhotoRepository PhotoRepository;

	@Autowired
	CategoryRepository CategoryRepository;
	
	@Autowired
	CommentRepository CommentRepository;

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

	@PostMapping("categories/create")
	public String store(
		@ModelAttribute("category") Category formCategory, 
		BindingResult bindingResult,
		Model model){
	
//		if (bindingResult.hasErrors())
//			return "Categories/index";
		
		CategoryRepository.save(formCategory);
		
		return "redirect:/categories";
		
	}
	
	@GetMapping("/edit/{id}")		//richieste GET del tipo /edit/xx
	public String edit(@PathVariable("id") Integer id, Model model) {		
		Category category=CategoryRepository.getReferenceById(id);  //lo recupero dal DB
		
		model.addAttribute("category", category);
//		model.addAttribute("elencoFoto", PhotoRepository.findAll());
		return "/categories/edit";
	}
	
	@PostMapping("/edit/{id}")		//richieste POST del tipo /edit/n
	public String update(
			@Valid @ModelAttribute Category formCategory,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors())
			return "categories/edit";
		
		else
			CategoryRepository.save(formCategory);
		
		
		
		return "redirect:/categories";
		
	}
	

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
	 
	   CategoryRepository.deleteById(id);
	   
	   return "redirect:/categories";
	}
	
}
