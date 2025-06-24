package school_management.com.CONTROLLERS;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import school_management.com.DTOS.CourseDto;
import school_management.com.SERVICES.CourseService;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    @Autowired
    private  CourseService courseService;

    private <T> ResponseEntity<Map<String, Object>> buildResponse(String message, T data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", message);
        response.put("data", data);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CourseDto dto) {
        CourseDto created = courseService.create(dto);
        return buildResponse("Course created", created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> get(@PathVariable Long id) {
        CourseDto found = courseService.getById(id);
        return buildResponse("Course found", found);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CourseDto> coursePage = courseService.getAll(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Course list");
        response.put("data", coursePage.getContent());
        response.put("currentPage", coursePage.getNumber());
        response.put("totalItems", coursePage.getTotalElements());
        response.put("totalPages", coursePage.getTotalPages());

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Valid @RequestBody CourseDto dto) {
        CourseDto updated = courseService.update(id, dto);
        return buildResponse("Course updated", updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        courseService.delete(id);
        return buildResponse("Course deleted", null);
    }
}
