package com.jkpbmz.technologiebackendoweprojekt.repositories;

import com.jkpbmz.technologiebackendoweprojekt.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByNip(String nip);
    Page<Client> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
