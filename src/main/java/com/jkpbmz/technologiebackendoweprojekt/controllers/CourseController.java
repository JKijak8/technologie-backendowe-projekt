package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping("")
    public CourseDTO findCourse(@RequestParam("courseId") Long courseId) {
        return courseService.fetchCourse(courseId);
    }

    @PostMapping("")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseSaveRequest request,
                                                    UriComponentsBuilder uriComponentsBuilder) {
        CourseDTO courseDTO = courseService.createCourse(request);
        URI uri = uriComponentsBuilder.path("/course?courseId={id}").buildAndExpand(courseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(courseDTO);
    }
}
