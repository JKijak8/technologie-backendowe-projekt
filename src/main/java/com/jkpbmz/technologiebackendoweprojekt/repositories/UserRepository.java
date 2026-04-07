package com.jkpbmz.technologiebackendoweprojekt.repositories;

import com.jkpbmz.technologiebackendoweprojekt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
}
