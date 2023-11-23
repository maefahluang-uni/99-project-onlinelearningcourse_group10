package th.mfu.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import th.mfu.models.users;
import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<users,Long> {

    public List<users> findByUsername(String username);
    public users findByusername(String username);
    public users findByRole(String role);
} 