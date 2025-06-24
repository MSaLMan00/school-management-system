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

import school_management.com.DTOS.StudentDto;
import school_management.com.SERVICES.StudentService;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    @Autowired
    private  StudentService studentService;

    private <T> ResponseEntity<Map<String, Object>> buildResponse(String message, T data) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", "success");
        body.put("message", message);
        body.put("data", data);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody StudentDto dto) {
        StudentDto created = studentService.create(dto);
        return buildResponse("Student created", created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> get(@PathVariable Long id) {
        StudentDto student = studentService.getById(id);
        return buildResponse("Student found", student);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentDto> pageResult = studentService.getAll(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Student list");
        response.put("data", pageResult.getContent());
        response.put("currentPage", pageResult.getNumber());
        response.put("totalItems", pageResult.getTotalElements());
        response.put("totalPages", pageResult.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Valid @RequestBody StudentDto dto) {
        StudentDto updated = studentService.update(id, dto);
        return buildResponse("Student updated", updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        studentService.delete(id);
        return buildResponse("Student deleted", null);
    }
}
