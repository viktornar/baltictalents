package lt.lessons.baltictalents.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "students")
public class Student extends Person {
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    private Set<Lesson> lessons;

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
}
