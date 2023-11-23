package th.mfu.repositories;


import org.springframework.data.repository.CrudRepository;
import java.util.List;
import th.mfu.models.Course;
import th.mfu.models.Orders;
import th.mfu.models.users;

public interface OrderRepository extends CrudRepository<Orders,Long> {
    public Orders findOrdersByUserAndCourse(users user, Course course);
    public Orders findOrdersByUserIdAndCourseId(Long userId, Long courseId);
    public List<Orders> findByUserId(Long id);
    public List<Orders> findByCourseId(Long id);
    public List<Orders> findByResponeAndUserId(Boolean respone,Long id);

}
