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
import jakarta.persistence.OneToMany;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    
    private BigDecimal price;
    private Date createDate;
    private String courseName;
    private String thumbnail_src;
    
    

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
    
}
