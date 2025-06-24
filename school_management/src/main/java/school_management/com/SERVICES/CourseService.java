package school_management.com.SERVICES;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school_management.com.DTOS.CourseDto;

public interface CourseService {

  CourseDto create(CourseDto dto);

  CourseDto getById(Long id);

  Page<CourseDto> getAll(Pageable pageable);

  CourseDto update(Long id, CourseDto dto);

  void delete(Long id);
}
