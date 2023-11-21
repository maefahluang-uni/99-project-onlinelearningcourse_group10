package th.mfu.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import th.mfu.models.CourseCategory;
import th.mfu.models.Course;

public interface CategoryRepository extends CrudRepository<CourseCategory,Long> {
    
        
}
