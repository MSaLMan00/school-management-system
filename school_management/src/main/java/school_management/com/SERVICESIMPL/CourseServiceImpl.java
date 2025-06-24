package school_management.com.SERVICESIMPL;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school_management.com.DTOS.CourseDto;
import school_management.com.MODELS.Course;
import school_management.com.REPOSITORIES.CourseRepository;
import school_management.com.SERVICES.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

  @Autowired
  private CourseRepository courseRepository;

  @Override
  public CourseDto create(CourseDto dto) {
    Course course = new Course();
    course.setName(dto.getName());
    course.setDescription(dto.getDescription());

    Course saved = courseRepository.save(course);
    return mapToDto(saved);
  }

  @Override
  public CourseDto getById(Long id) {
    Course course = courseRepository.findById(id)
                        .orElseThrow(
                            () -> new EntityNotFoundException("Course not found with ID: " + id));
    return mapToDto(course);
  }

  @Override
  public Page<CourseDto> getAll(Pageable pageable) {
    return courseRepository.findAll(pageable)
               .map(this::mapToDto); // Assuming you have a method to convert Course -> CourseDto
  }

  @Override
  public CourseDto update(Long id, CourseDto dto) {
    Course course = courseRepository.findById(id)
                        .orElseThrow(
                            () -> new EntityNotFoundException("Course not found with ID: " + id));

    course.setName(dto.getName());
    course.setDescription(dto.getDescription());

    Course updated = courseRepository.save(course);
    return mapToDto(updated);
  }

  @Override
  public void delete(Long id) {
    if (!courseRepository.existsById(id)) {
      throw new EntityNotFoundException("Course not found with ID: " + id);
    }
    courseRepository.deleteById(id);
  }

  private CourseDto mapToDto(Course course) {
    CourseDto dto = new CourseDto();
    dto.setId(course.getId());
    dto.setName(course.getName());
    dto.setDescription(course.getDescription());
    return dto;
  }
}
