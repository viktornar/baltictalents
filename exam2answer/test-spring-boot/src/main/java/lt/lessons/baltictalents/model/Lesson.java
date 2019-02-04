package lt.lessons.baltictalents.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="lessons")
public class Lesson extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String type;
    private String description;
    private String course;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    @Transient
    @JsonIgnore
    private Integer selectedStudentId;
    
    @Transient
    @JsonIgnore
    private Integer selectedTeacherId;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the selectedTeacherId
     */
    public Integer getSelectedTeacherId() {
        return selectedTeacherId;
    }

    /**
     * @param selectedTeacherId the selectedTeacherId to set
     */
    public void setSelectedTeacherId(Integer selectedTeacherId) {
        this.selectedTeacherId = selectedTeacherId;
    }

    /**
     * @return the selectedStudentId
     */
    public Integer getSelectedStudentId() {
        return selectedStudentId;
    }

    /**
     * @param selectedStudentId the selectedStudentId to set
     */
    public void setSelectedStudentId(Integer selectedStudentId) {
        this.selectedStudentId = selectedStudentId;
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @return the teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * @return the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
