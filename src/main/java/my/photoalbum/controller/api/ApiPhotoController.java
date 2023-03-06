package my.photoalbum.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.photoalbum.model.Comment;
import my.photoalbum.model.Photo;
import my.photoalbum.repository.CategoryRepository;
import my.photoalbum.repository.CommentRepository;
import my.photoalbum.repository.PhotoRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiPhotoController {

	@Autowired
	PhotoRepository PhotoRepository;
	
	@Autowired
	CategoryRepository CategoryRepository;
	
	@Autowired
	CommentRepository CommentRepository;
	
	@GetMapping()
	public List<Photo> index() {
		return PhotoRepository.findAll();
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<Photo> show(@PathVariable("id") Integer id) {
		Optional<Photo> res=PhotoRepository.findById(id);
		if (res.isPresent()) 
			return new ResponseEntity<Photo>(res.get(), HttpStatus.OK);
	    else
	    	return new ResponseEntity<Photo>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/comments")
	public List<Comment> commentIndex() {
		return CommentRepository.findAll();
	}
	
	@PostMapping()
	public Comment create(@RequestBody Comment comment) {
		return CommentRepository.save(comment);
	}

}
