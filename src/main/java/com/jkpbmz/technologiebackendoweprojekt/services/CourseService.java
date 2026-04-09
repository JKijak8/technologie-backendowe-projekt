package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.entities.Course;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.NotFoundException;
import com.jkpbmz.technologiebackendoweprojekt.mappers.CourseMapper;
import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseDTO;
import com.jkpbmz.technologiebackendoweprojekt.repositories.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CourseService {
    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    public CourseDTO fetchCourse(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new NotFoundException("Course not found");
        }
        return courseMapper.toCourseDTO(course);
    }
}
