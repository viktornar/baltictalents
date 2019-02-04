package lt.lessons.baltictalents.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.lessons.baltictalents.model.Student;
import lt.lessons.baltictalents.repository.StudentRepository;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students/{id}")
    Optional<Student> getStudent(@PathVariable Integer id) {
        return studentRepository.findById(id);
    }

    @GetMapping("/students")
    List<Student> getStudents() {
        return studentRepository.findAll();
    }
}
