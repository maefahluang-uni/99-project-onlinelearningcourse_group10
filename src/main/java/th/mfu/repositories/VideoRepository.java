package th.mfu.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.models.Video;



public interface VideoRepository extends CrudRepository<Video,Long> {
    
    public List<Video> deleteByCourseId(long course_id);
    public List<Video> findByCourseId(Long course_id);
    public Video findByCourseId(long course_id);
}