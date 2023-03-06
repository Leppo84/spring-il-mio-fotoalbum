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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import my.photoalbum.model.Photo;
import my.photoalbum.repository.CategoryRepository;
import my.photoalbum.repository.CommentRepository;
import my.photoalbum.repository.PhotoRepository;

@Controller
@RequestMapping("/photos")
public class PhotoController {
	
	@Autowired
	PhotoRepository PhotoRepository;
	
	@Autowired
	CategoryRepository CategoryRepository;
	
	@Autowired
	CommentRepository CommentRepository;

	@GetMapping
	public String index(@RequestParam(name = "input", required = false) String input, Model model) {
		List<Photo> elencoFoto;	
		if (input != null && !input.isEmpty()) {
			elencoFoto = PhotoRepository.findByTitleLike("%" + input + "%");
		} else {
			elencoFoto = PhotoRepository.findAll();
		}
		model.addAttribute("elencoFoto", elencoFoto);
		return "photos/index";
	}
	
	@GetMapping("/create")		//gestirà le richieste GET di tipo /create
	public String create(Model model) {
		Photo photo=new Photo();	//non esiste ancora sul DB
		photo.setUrl("https://picsum.photos/400");
		model.addAttribute("photo", photo);
		model.addAttribute("elencoCategorie", CategoryRepository.findAll());
		
		return "photos/create";
	}
	
	@PostMapping("/photos/create")  	//gestirà le richieste di tipo POST di tipo /create
	public String store(
		@Valid @ModelAttribute("photo") Photo formPhoto, 
		BindingResult bindingResult,
		Model model){
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "Qualcosa è andato storto :(");
			return "/photos/create";
			}
		
		PhotoRepository.save(formPhoto);
		
		return "redirect:/photos"; //genera un altro get
		
	}
	
	@GetMapping("/{id}")
	public String showPhoto(@PathVariable("id") Integer id, Model model) {
		
		Photo showPhoto = PhotoRepository.getReferenceById(id);
		
		model.addAttribute("photo", showPhoto);

		return "/photos/show";

	}
	
	@GetMapping("/edit/{id}")		//richieste GET del tipo /edit/xx
	public String edit(@PathVariable("id") Integer id, Model model) {		
		Photo photo=PhotoRepository.getReferenceById(id);  //lo recupero dal DB
		
		model.addAttribute("photo", photo);
		model.addAttribute("elencoCategorie", CategoryRepository.findAll());
		return "/photos/edit";
	}
	
	@PostMapping("/edit/{id}")		//richieste POST del tipo /edit/n
	public String update(
			@Valid @ModelAttribute Photo formPhoto,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors())
			return "photos/edit";
		
		else
			PhotoRepository.save(formPhoto);
		
		
		
		return "redirect:/photos";
		
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
	 
	   PhotoRepository.deleteById(id);
	   
	   return "redirect:/photos";
	}
	
	
}
