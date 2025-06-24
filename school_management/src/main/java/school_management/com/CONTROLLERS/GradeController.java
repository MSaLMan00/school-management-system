package school_management.com.CONTROLLERS;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import school_management.com.DTOS.GradeDto;
import school_management.com.SERVICES.GradeService;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {

    @Autowired
    private  GradeService gradeService;

    private <T> ResponseEntity<Map<String, Object>> buildResponse(String message, T data) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", "success");
        body.put("message", message);
        body.put("data", data);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> assignGrade(@Valid @RequestBody GradeDto dto) {
        GradeDto created = gradeService.assignGrade(dto);
        return buildResponse("Grade assigned", created);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Map<String, Object>> getGradesByStudent(@PathVariable Long studentId) {
        List<GradeDto> grades = gradeService.getGradesByStudentId(studentId);
        return buildResponse("Grades for student", grades);
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Map<String, Object>> getGradeByStudentAndCourse(
        @PathVariable Long studentId,
        @PathVariable Long courseId) {
        GradeDto grade = gradeService.getGradeByStudentAndCourse(studentId, courseId);
        return buildResponse("Grade found", grade);
    }
}
