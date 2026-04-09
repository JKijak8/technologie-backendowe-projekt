package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.entities.Position;
import com.jkpbmz.technologiebackendoweprojekt.mappers.PositionMapper;
import com.jkpbmz.technologiebackendoweprojekt.projections.position.PositionDTO;
import com.jkpbmz.technologiebackendoweprojekt.repositories.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PositionService {
    private PositionRepository positionRepository;

    private PositionMapper positionMapper;

    public List<PositionDTO> fetchAllPositions() {
        List<Position> positions = positionRepository.findAll();
        return positions.stream().map(positionMapper::toPositionDTO).collect(Collectors.toList());
    }
}
