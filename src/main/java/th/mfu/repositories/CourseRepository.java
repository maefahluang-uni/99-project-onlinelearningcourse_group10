package th.mfu.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import th.mfu.models.CourseCategory;
import th.mfu.models.Course;
import th.mfu.models.Video;
@Repository
public interface CourseRepository extends CrudRepository<Course,Long> {
    public List<Course> findByCategoryId(Long id);
    
}