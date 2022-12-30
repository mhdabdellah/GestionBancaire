package RIM.Banque.GestionBancaire.repository;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import RIM.Banque.GestionBancaire.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Optional<User> findByEmail(String email);
    // Optional<User> findByUsernameOrEmail(String username);
    User findByUsername(String username);

    // Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

    // Boolean existsByEmail(String email);
}
