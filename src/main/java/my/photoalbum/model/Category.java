package my.photoalbum.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="Nome non valido!")
	@NotEmpty(message="Nome non valido!")
	private String name;
	
	@JsonBackReference
	@ManyToMany(mappedBy = "categories")
	private List<Photo> photos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
}
	