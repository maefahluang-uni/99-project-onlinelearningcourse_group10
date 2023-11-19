package th.mfu.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FAQ {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "FAQ_id")
    private Long id;

    private Date faqUploadDate;
    private String faqQuestion;
    private String faqAnswer;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getFaqUploadDate() {
        return faqUploadDate;
    }
    public void setFaqUploadDate(Date faqUploadDate) {
        this.faqUploadDate = faqUploadDate;
    }
    public String getFaqQuestion() {
        return faqQuestion;
    }
    public void setFaqQuestion(String faqQuestion) {
        this.faqQuestion = faqQuestion;
    }
    public String getFaqAnswer() {
        return faqAnswer;
    }
    public void setFaqAnswer(String faqAnswer) {
        this.faqAnswer = faqAnswer;
    }
    

}
