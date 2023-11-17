package th.mfu.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long id;

    private String title;
    private String description;
    private String path;
    
    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "course_id")
    private Course course;
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
   

    // Getters and setters
    
}