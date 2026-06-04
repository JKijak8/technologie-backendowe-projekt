package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.projections.load.LoadDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.load.LoadSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.services.LoadService;
import com.jkpbmz.technologiebackendoweprojekt.projections.load.LoadSummaryDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/load")
public class LoadController {

    private final LoadService loadService;

    @GetMapping("/list")
    public ResponseEntity<Page<LoadSummaryDTO>> getLoads(Pageable pageable) {
        Page<LoadSummaryDTO> loads = loadService.getLoads(pageable);
        return ResponseEntity.ok(loads);
    }

    @GetMapping("")
    public ResponseEntity<LoadDTO> getLoad(@RequestParam("loadId") Long loadId) {
        LoadDTO load = loadService.getLoad(loadId);
        return ResponseEntity.ok(load);
    }

    @PostMapping("")
    public ResponseEntity<LoadDTO> createLoad(@RequestBody @Valid LoadSaveRequest request,
                                              UriComponentsBuilder uriBuilder) {
        LoadDTO dto = loadService.createLoad(request);
        URI uri = uriBuilder.path("/load?loadId={id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PatchMapping("/assign-to-course")
    public ResponseEntity<Void> assignLoadsToCourse(@RequestParam("courseId") Long courseId,
                                                    @RequestBody List<Long> loadIds) {
        loadService.assignLoadsToCourse(courseId, loadIds);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteLoad(@RequestParam("loadId") Long loadId) {
        loadService.deleteLoad(loadId);
        return ResponseEntity.noContent().build();
    }
}
