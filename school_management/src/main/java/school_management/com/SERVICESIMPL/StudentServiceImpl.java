package school_management.com.SERVICESIMPL;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import school_management.com.DTOS.CourseDto;
import school_management.com.DTOS.GradeDto;
import school_management.com.DTOS.StudentDto;
import school_management.com.MODELS.Student;
import school_management.com.REPOSITORIES.StudentRepository;
import school_management.com.SERVICES.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDto create(StudentDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        Student saved = studentRepository.save(student);
        return mapToDto(saved);
    }

    @Override
    public StudentDto getById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
        return mapToDto(student);
    }

    @Override
    public Page<StudentDto> getAll(Pageable pageable) {
      return studentRepository.findAll(pageable)
                 .map(this::mapToDto); // automatically maps each Student to StudentDto
    }


  @Override
    public StudentDto update(Long id, StudentDto dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());

        Student updated = studentRepository.save(student);
        return mapToDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    private StudentDto mapToDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());

        // Map Courses
        List<CourseDto> courses = student.getCourses().stream()
                                      .map(course -> {
                                          CourseDto courseDto = new CourseDto();
                                          courseDto.setId(course.getId());
                                          courseDto.setName(course.getName());
                                          courseDto.setDescription(course.getDescription());
                                          return courseDto;
                                      })
                                      .collect(Collectors.toList());
        dto.setEnrolledCourses(courses);

// Map Grades
        List<GradeDto> grades = student.getGrades().stream()
                                    .map(grade -> {
                                        GradeDto gradeDto = new GradeDto();
                                        gradeDto.setId(grade.getId());
                                        gradeDto.setStudentId(grade.getStudent().getId());
                                        gradeDto.setCourseId(grade.getCourse().getId());
                                        gradeDto.setScore(grade.getScore()); // or grade.setGradeValue()
                                        return gradeDto;
                                    })
                                    .collect(Collectors.toList());
        dto.setGrades(grades);

        return dto;
    }

}
