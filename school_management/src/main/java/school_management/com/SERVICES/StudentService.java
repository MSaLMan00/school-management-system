package school_management.com.SERVICES;



import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school_management.com.DTOS.StudentDto;

public interface StudentService {
    StudentDto create(StudentDto dto);
    StudentDto getById(Long id);
    public Page<StudentDto> getAll(Pageable pageable);

    StudentDto update(Long id, StudentDto dto);
    void delete(Long id);
}
