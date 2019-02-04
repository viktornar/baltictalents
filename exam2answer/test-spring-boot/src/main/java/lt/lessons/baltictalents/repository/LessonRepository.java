package lt.lessons.baltictalents.repository;

import lt.lessons.baltictalents.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    Lesson findByType(String type);
}
