package my.photoalbum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import my.photoalbum.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
	
	public List<Photo> findByTitleLike(String input);	
	public List<Photo> findByTagLike(String input);	

}
