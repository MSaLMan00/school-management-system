package school_management.com.SERVICES;



import java.util.List;
import school_management.com.DTOS.GradeDto;

public interface GradeService {
    GradeDto assignGrade(GradeDto dto);
    List<GradeDto> getGradesByStudentId(Long studentId);
    GradeDto getGradeByStudentAndCourse(Long studentId, Long courseId);
}
