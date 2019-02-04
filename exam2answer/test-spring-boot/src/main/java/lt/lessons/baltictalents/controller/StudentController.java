package lt.lessons.baltictalents.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lt.lessons.baltictalents.model.Lesson;
import lt.lessons.baltictalents.model.Student;
import lt.lessons.baltictalents.repository.LessonRepository;
import lt.lessons.baltictalents.repository.StudentRepository;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/student/new")
    String getStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @PostMapping("/student/add")
    String postLessonForm(@Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "student-form";
        } else {
            student = this.studentRepository.save(student);
            return "redirect:/api/students/" + student.getId();
        }
    }
}
