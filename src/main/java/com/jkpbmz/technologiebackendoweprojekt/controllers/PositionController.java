package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.projections.position.PositionDTO;
import com.jkpbmz.technologiebackendoweprojekt.services.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @PostMapping("")
    public ResponseEntity<PositionDTO> createPosition(@RequestParam("position") String position,
                                                      UriComponentsBuilder uriComponentsBuilder) {
        PositionDTO positionDTO = positionService.createPosition(position);
        URI uri = uriComponentsBuilder.path("/position?positionId={id}").buildAndExpand(positionDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(positionDTO);
    }
}
