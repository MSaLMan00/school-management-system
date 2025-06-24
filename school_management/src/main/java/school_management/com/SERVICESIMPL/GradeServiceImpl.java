package school_management.com.SERVICESIMPL;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import school_management.com.DTOS.GradeDto;
import school_management.com.MODELS.Course;
import school_management.com.MODELS.Grade;
import school_management.com.MODELS.Student;
import school_management.com.REPOSITORIES.CourseRepository;
import school_management.com.REPOSITORIES.GradeRepository;
import school_management.com.REPOSITORIES.StudentRepository;
import school_management.com.SERVICES.GradeService;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public GradeDto assignGrade(GradeDto dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        Grade grade = gradeRepository
                .findByStudentIdAndCourseId(dto.getStudentId(), dto.getCourseId())
                .orElse(new Grade());

        grade.setStudent(student);
        grade.setCourse(course);
        grade.setScore(dto.getScore());

        Grade saved = gradeRepository.save(grade);
        return mapToDto(saved);
    }

    @Override
    public List<GradeDto> getGradesByStudentId(Long studentId) {
        List<Grade> grades = gradeRepository.findByStudentId(studentId);
        return grades.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public GradeDto getGradeByStudentAndCourse(Long studentId, Long courseId) {
        Grade grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new EntityNotFoundException("Grade not found"));
        return mapToDto(grade);
    }

    private GradeDto mapToDto(Grade grade) {
        GradeDto dto = new GradeDto();
        dto.setId(grade.getId());
        dto.setStudentId(grade.getStudent().getId());
        dto.setCourseId(grade.getCourse().getId());
        dto.setScore(grade.getScore());
        return dto;
    }
}
