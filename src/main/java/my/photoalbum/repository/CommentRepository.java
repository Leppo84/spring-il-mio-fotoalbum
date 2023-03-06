package my.photoalbum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.photoalbum.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
