package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.exceptions.BadRequestException;
import com.jkpbmz.technologiebackendoweprojekt.projections.delivery_state.DeliveryStateDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.delivery_state.DeliveryStateSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.services.DeliveryStateService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/delivery-state")
public class DeliveryStateController {
    private static final int MAX_PAGE_SIZE = 25;

    private final DeliveryStateService deliveryStateService;

    @GetMapping("")
    public DeliveryStateDTO findDeliveryState(@RequestParam("deliveryStateId") Long deliveryStateId) {
        return deliveryStateService.fetchDeliveryState(deliveryStateId);
    }

    @GetMapping("/list")
    public Page<DeliveryStateDTO> findDeliveryStateList(Pageable pageable) {
        if (pageable.getPageSize() > MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size exceeds maximum. Maximum allowed is " + MAX_PAGE_SIZE);
        }
        return deliveryStateService.fetchDeliveryStateList(pageable);
    }

    @PostMapping("")
    public ResponseEntity<DeliveryStateDTO> createDeliveryState(@RequestBody DeliveryStateSaveRequest request,
                                                                UriComponentsBuilder uriBuilder) {
        DeliveryStateDTO dto = deliveryStateService.createDeliveryState(request);
        URI uri = uriBuilder.path("/delivery-state?deliveryStateId={id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("")
    public DeliveryStateDTO updateDeliveryState(@RequestParam("deliveryStateId") Long deliveryStateId,
                                                @RequestBody DeliveryStateSaveRequest request) {
        return deliveryStateService.updateDeliveryState(deliveryStateId, request);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteDeliveryState(@RequestParam("deliveryStateId") Long deliveryStateId) {
        deliveryStateService.deleteDeliveryState(deliveryStateId);
        return ResponseEntity.noContent().build();
    }
}
