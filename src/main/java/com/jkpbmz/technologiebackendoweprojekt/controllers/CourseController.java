package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.exceptions.BadRequestException;
import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {
    private static final int MAX_PAGE_SIZE = 25;

    private final CourseService courseService;

    @GetMapping("")
    public CourseDTO findCourse(@RequestParam("courseId") Long courseId) {
        return courseService.fetchCourse(courseId);
    }

    @GetMapping("/list")
    public Page<CourseSummaryDTO> findCourseList(Pageable pageable) {
        if (pageable.getPageSize() > MAX_PAGE_SIZE) throw new BadRequestException("Page size is greater than MAX_PAGE_SIZE");

        return courseService.fetchCourseList(pageable);
    }

    @PostMapping("")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseSaveRequest request,
                                                    UriComponentsBuilder uriComponentsBuilder) {
        CourseDTO courseDTO = courseService.createCourse(request);
        URI uri = uriComponentsBuilder.path("/course?courseId={id}").buildAndExpand(courseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(courseDTO);
    }

    @PutMapping("")
    public CourseDTO updateCourse(@RequestParam("courseId") Long courseId, @RequestBody CourseSaveRequest request) {
        return courseService.updateCourse(courseId, request);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteCourse(@RequestParam("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
}
