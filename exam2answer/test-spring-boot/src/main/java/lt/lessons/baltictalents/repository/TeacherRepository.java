package lt.lessons.baltictalents.repository;

import lt.lessons.baltictalents.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
