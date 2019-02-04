package lt.lessons.baltictalents.repository;

import lt.lessons.baltictalents.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
