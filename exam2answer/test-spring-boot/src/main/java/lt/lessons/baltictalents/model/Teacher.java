package lt.lessons.baltictalents.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

@Entity
@Table(name="teachers")
public class Teacher extends Person {
    private static final long serialVersionUID = 1L;
    private String title;

    @OneToMany(mappedBy = "teacher")
    @JsonManagedReference
    private Set<Lesson> lessons;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the lessons
     */
    public Set<Lesson> getLessons() {
        return lessons;
    }

    /**
     * @param lessons the lessons to set
     */
    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
