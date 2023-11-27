package th.mfu.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//6 repositories
import th.mfu.models.CourseCategory;
import th.mfu.models.Course;
@Repository
public interface CategoryRepository extends CrudRepository<CourseCategory,Long> {
    
        
}
