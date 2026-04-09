package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseDTO;
import com.jkpbmz.technologiebackendoweprojekt.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping("")
    public CourseDTO findCourse(@RequestParam("courseId") Long courseId) {
        return courseService.fetchCourse(courseId);
    }
}
