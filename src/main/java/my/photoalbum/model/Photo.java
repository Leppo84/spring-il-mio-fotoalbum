package my.photoalbum.model;

import java.util.List;
//import java.util.Locale.Category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="photos")
public class Photo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="Titolo non valido!")
	@NotEmpty(message="Titolo non valido!")
	private String title;

	@NotNull(message="Descrizione non valida!")
	@NotEmpty(message="Descrizione non valida!")
	private String description;
	
	@NotNull(message="Percorso non valido!")
	@NotEmpty(message="Percorso non valido!")
	private String url;
	
	@NotNull(message="Tag non valido!")
	@NotEmpty(message="Tag non valido!")
	private String tag;
	
	private boolean visible;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Category> categories;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public boolean isVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	public List<Category> getCategories() {
		return categories;
	}


	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
	