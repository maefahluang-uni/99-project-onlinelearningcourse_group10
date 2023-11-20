package th.mfu.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.models.Course;
import th.mfu.models.Video;

public interface CourseRepository extends CrudRepository<Course,Long> {
    
}