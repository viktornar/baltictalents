package lt.lessons.baltictalents.controller;

import lt.lessons.baltictalents.model.Lesson;
import lt.lessons.baltictalents.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class LessonRestController {
    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping("/lessons/{id}")
    Optional<Lesson> getLesson(@PathVariable Integer id) {
        return lessonRepository.findById(id);
    }

    @GetMapping("/lessons")
    List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }
}
