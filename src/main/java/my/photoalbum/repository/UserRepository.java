package my.photoalbum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import my.photoalbum.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

}
