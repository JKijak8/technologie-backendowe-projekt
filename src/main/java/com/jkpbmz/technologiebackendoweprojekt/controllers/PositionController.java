package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.projections.position.PositionDTO;
import com.jkpbmz.technologiebackendoweprojekt.services.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/position")
public class PositionController {
    private final PositionService positionService;

    @GetMapping("")
    public List<PositionDTO> findAllPositions() {
        return positionService.fetchAllPositions();
    }
}
