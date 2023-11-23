package th.mfu.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

     @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "user_id")
    private users user;

    private Boolean respone;

    public Orders(){
        this.respone = false;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public users getUser() {
        return user;
    }

    public void setUser(users user) {
        this.user = user;
    }


    public Boolean getRespone() {
        return respone;
    }


    public void setRespone(Boolean respone) {
        this.respone = respone;
    }

    
}
