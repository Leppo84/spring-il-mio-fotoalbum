package my.photoalbum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.photoalbum.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
