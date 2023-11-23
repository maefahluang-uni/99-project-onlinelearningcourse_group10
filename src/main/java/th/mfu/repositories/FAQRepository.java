package th.mfu.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import th.mfu.models.FAQ;
@Repository
public interface FAQRepository extends CrudRepository<FAQ,Long>  {

    
}  

