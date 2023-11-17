package th.mfu.repositories;

import org.springframework.data.repository.CrudRepository;

import th.mfu.models.Course;

public interface CourseRepository extends CrudRepository<Course,Long> {
    
}