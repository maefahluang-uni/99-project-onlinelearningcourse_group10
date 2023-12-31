package th.mfu.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Course {

    //6 Models
    /*GenerationType.IDENTITY to let the !database! handle the auto incrementing of the id
    Mysql not support sequence
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    
    private BigDecimal price;
    private Date createDate;
    private String courseName;
    private String thumbnail_src;
    private String intro_src;
    private String course_description;

  

    //When an entity is merged (updated), the same operation will be applied to associated entities.
    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "category_id")
    private CourseCategory category;
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getIntro_src() {
        return intro_src;
    }
    public void setIntro_src(String intro_src) {
        this.intro_src = intro_src;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getThumbnail_src() {
        return thumbnail_src;
    }
    public void setThumbnail_src(String thumbnail_src) {
        this.thumbnail_src = thumbnail_src;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCourse_description() {
        return course_description;
    }
    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }
   /* public String getCourseCategory() {
        return courseCategory;
    }
    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    } */
    public CourseCategory getCategory() {
        return category;
    }
    public void setCategory(CourseCategory category) {
        this.category = category;
    }
      /*CascadeType.REPLICATE is a cascading type in Hibernate that specifies that the replicate operation should be cascaded from the parent entity to the child entities.
    When CascadeType.REPLICATE is used, any child entities associated with a parent entity will be automatically replicated when the parent entity is replicated. This means that new child entities will be created and persisted in the database with the same state as the original child entities. */
    //CascadeType.REMOVE is a cascading type in Hibernate that specifies that the delete operation should be cascaded from the parent entity to the child entities.
    //CascadeType.Refresh So if entity A has a reference to entity B, and that reference is annotated with @CascadeType.REFRESH, and EntityManager.refresh(A) is called, then EntityManager.refresh(B) is implicitly called also.
    //CascadeType.ALL is a cascading type in Hibernate that specifies that all state transitions (create, update, delete, and refresh) should be cascaded from the parent entity to the child entities.
    //When an entity is persisted (Create,save), the same operation will be applied to associated entities.
    //AAAAAAAAAAAH
    
}
