package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.enums.RoleEnum;
import com.jkpbmz.technologiebackendoweprojekt.exceptions.BadRequestException;
import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseDTO;
import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseSaveRequest;
import com.jkpbmz.technologiebackendoweprojekt.projections.course.CourseSummaryDTO;
import com.jkpbmz.technologiebackendoweprojekt.services.CourseService;
import com.jkpbmz.technologiebackendoweprojekt.services.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {
    private static final int MAX_PAGE_SIZE = 25;

    private final CourseService courseService;
    private final JwtService jwtService;

    @GetMapping("")
    public CourseDTO findCourse(@RequestParam("courseId") Long courseId) {
        return courseService.fetchCourse(courseId);
    }

    @GetMapping("/list")
    public Page<CourseSummaryDTO> findCourseList(@RequestHeader(value="Authorization") String authorization,
                                                 Pageable pageable) {
        if (pageable.getPageSize() > MAX_PAGE_SIZE) throw new BadRequestException("Page size is greater than MAX_PAGE_SIZE");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        Long userId = (Long) authentication.getPrincipal();

        String token = authorization.replace("Bearer ", "");
        List<RoleEnum> userRoles = jwtService.parseToken(token).getRoles();

        return courseService.fetchCourseList(pageable, userId, userRoles);
    }

    @PostMapping("")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody @Valid CourseSaveRequest request,
                                                    UriComponentsBuilder uriComponentsBuilder) {
        CourseDTO courseDTO = courseService.createCourse(request);
        URI uri = uriComponentsBuilder.path("/course?courseId={id}").buildAndExpand(courseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(courseDTO);
    }

    @PutMapping("")
    public CourseDTO updateCourse(@RequestParam("courseId") Long courseId,
                                  @RequestBody @Valid CourseSaveRequest request) {
        return courseService.updateCourse(courseId, request);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteCourse(@RequestParam("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
}
