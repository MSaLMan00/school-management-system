package school_management.com.REPOSITORIES;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school_management.com.MODELS.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
