package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.entities.Contract;
import com.jkpbmz.technologiebackendoweprojekt.entities.Course;
import com.jkpbmz.technologiebackendoweprojekt.entities.DeliveryState;
import com.jkpbmz.technologiebackendoweprojekt.entities.Load;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.ConflictException;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.NotFoundException;
import com.jkpbmz.technologiebackendoweprojekt.mappers.LoadMapper;
import com.jkpbmz.technologiebackendoweprojekt.projections.LoadDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.LoadSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.repositories.ContractRepository;
import com.jkpbmz.technologiebackendoweprojekt.repositories.CourseRepository;
import com.jkpbmz.technologiebackendoweprojekt.repositories.DeliveryStateRepository;
import com.jkpbmz.technologiebackendoweprojekt.repositories.LoadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class LoadService {
    private final LoadRepository loadRepository;
    private final ContractRepository contractRepository;
    private final DeliveryStateRepository deliveryStateRepository;
    private final CourseRepository courseRepository;
    private final LoadMapper loadMapper;

    @Transactional
    public LoadDTO createLoad(LoadSaveRequest request) {
        if (loadRepository.existsByIdentifier(request.getIdentifier())) {
            throw new ConflictException("Load with identifier " + request.getIdentifier() + " already exists.");
        }

        Contract contract = contractRepository.findById(request.getContractId())
                .orElseThrow(() -> new NotFoundException("Contract not found"));

        DeliveryState deliveryState = deliveryStateRepository.findById(request.getDeliveryStateId())
                .orElseThrow(() -> new NotFoundException("Delivery State not found"));

        Load load = loadMapper.toLoad(request);
        load.setContract(contract);
        load.setDeliveryState(deliveryState);

        loadRepository.save(load);
        return loadMapper.toLoadDTO(load);
    }

    @Transactional
    public void assignLoadsToCourse(Long courseId, List<Long> loadIds) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found"));
        List<Load> loads = loadRepository.findAllById(loadIds);

        if (loads.isEmpty()) {
            throw new NotFoundException("No loads found for provided IDs");
        }
        loads.forEach(load -> load.setCourse(course));
        loadRepository.saveAll(loads);
    }

    @Transactional
    public void deleteLoad(Long loadId) {
        Load load = loadRepository.findById(loadId)
                .orElseThrow(() -> new NotFoundException("Load not found"));

        loadRepository.delete(load);
    }
}
