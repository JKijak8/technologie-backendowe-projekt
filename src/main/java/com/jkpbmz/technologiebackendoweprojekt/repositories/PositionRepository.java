package com.jkpbmz.technologiebackendoweprojekt.repositories;

import com.jkpbmz.technologiebackendoweprojekt.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    boolean existsByPosition(String position);
}
