package school_management.com.REPOSITORIES;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import school_management.com.MODELS.Course;
import school_management.com.MODELS.Grade;
import school_management.com.MODELS.Student;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    Optional<Grade> findByStudentAndCourse(Student student, Course course);

    List<Grade> findAllByStudent(Student student);

    Optional<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);

    List<Grade> findByStudentId(Long studentId);
}
