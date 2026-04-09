package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.entities.Course;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.NotFoundException;
import com.jkpbmz.technologiebackendoweprojekt.mappers.CourseMapper;
import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.repositories.CourseRepository;
import com.jkpbmz.technologiebackendoweprojekt.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final EmployeeRepository employeeRepository;

    private final CourseMapper courseMapper;

    public CourseDTO fetchCourse(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new NotFoundException("Course not found");
        }
        return courseMapper.toCourseDTO(course);
    }

    public CourseDTO createCourse(CourseSaveRequest request) {
        Course course = courseMapper.toCourse(request, employeeRepository);
        courseRepository.save(course);

        return courseMapper.toCourseDTO(course);
    }

    public CourseDTO updateCourse(Long id, CourseSaveRequest request) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new NotFoundException("Course not found");
        }
        courseMapper.updateCourse(request, course, employeeRepository);
        courseRepository.save(course);
        return courseMapper.toCourseDTO(course);
    }
}
