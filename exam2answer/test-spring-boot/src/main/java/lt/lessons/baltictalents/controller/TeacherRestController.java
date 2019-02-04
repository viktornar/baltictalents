package lt.lessons.baltictalents.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.lessons.baltictalents.model.Teacher;
import lt.lessons.baltictalents.repository.TeacherRepository;

@RestController
@RequestMapping("/api")
public class TeacherRestController {
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/teachers/{id}")
    Optional<Teacher> getStudent(@PathVariable Integer id) {
        return teacherRepository.findById(id);
    }

    @GetMapping("/teachers")
    List<Teacher> getStudents() {
        return teacherRepository.findAll();
    }
}
