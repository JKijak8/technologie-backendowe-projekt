package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.entities.Position;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.ConflictException;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.NotFoundException;
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

    public PositionDTO fetchPosition(Long id) {
        return positionMapper.toPositionDTO(getPosition(id));
    }

    public List<PositionDTO> fetchAllPositions() {
        List<Position> positions = positionRepository.findAll();
        return positions.stream().map(positionMapper::toPositionDTO).collect(Collectors.toList());
    }

    public PositionDTO createPosition(String name) {
        checkIfPositionExists(name);

        Position position = new Position();
        position.setPosition(name);

        positionRepository.save(position);
        return positionMapper.toPositionDTO(position);
    }

    public void deletePosition(Long id) {
        positionRepository.findById(id).orElseThrow(() -> new NotFoundException("Position not found"));
        positionRepository.deleteById(id);
    }

    public Position getPosition(Long id) {
        Position position = positionRepository.findById(id).orElse(null);
        if (position == null) {
            throw new NotFoundException("Position not found");
        }
        return position;
    }

    private void checkIfPositionExists(String position) {
        if (positionRepository.existsByPosition(position)) throw new ConflictException("Position with this name already exists.");
    }
}
