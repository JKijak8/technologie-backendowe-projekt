package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.entities.DeliveryState;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.NotFoundException;
import com.jkpbmz.technologiebackendoweprojekt.mappers.DeliveryStateMapper;
import com.jkpbmz.technologiebackendoweprojekt.projections.delivery_state.DeliveryStateDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.delivery_state.DeliveryStateSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.repositories.DeliveryStateRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeliveryStateService {
    private final DeliveryStateRepository deliveryStateRepository;
    private final DeliveryStateMapper deliveryStateMapper;

    public DeliveryStateDTO fetchDeliveryState(Long id) {
        DeliveryState state = deliveryStateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Delivery state not found"));
        return deliveryStateMapper.toDeliveryStateDTO(state);
    }

    public Page<DeliveryStateDTO> fetchDeliveryStateList(Pageable pageable) {
        return deliveryStateRepository.findAll(pageable)
                .map(deliveryStateMapper::toDeliveryStateDTO);
    }

    public DeliveryStateDTO createDeliveryState(DeliveryStateSaveRequest request) {
        DeliveryState state = deliveryStateMapper.toDeliveryState(request);
        deliveryStateRepository.save(state);
        return deliveryStateMapper.toDeliveryStateDTO(state);
    }

    public DeliveryStateDTO updateDeliveryState(Long id, DeliveryStateSaveRequest request) {
        DeliveryState state = deliveryStateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Delivery state not found"));

        deliveryStateMapper.updateDeliveryState(request, state);
        deliveryStateRepository.save(state);

        return deliveryStateMapper.toDeliveryStateDTO(state);
    }

    public void deleteDeliveryState(Long id) {
        DeliveryState state = deliveryStateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Delivery state not found"));
        deliveryStateRepository.delete(state);
    }
}
