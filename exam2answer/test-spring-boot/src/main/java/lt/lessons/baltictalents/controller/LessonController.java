package lt.lessons.baltictalents.controller;

import lt.lessons.baltictalents.model.Lesson;
import lt.lessons.baltictalents.model.Student;
import lt.lessons.baltictalents.model.Teacher;
import lt.lessons.baltictalents.repository.LessonRepository;
import lt.lessons.baltictalents.repository.StudentRepository;
import lt.lessons.baltictalents.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LessonController {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/lesson/new")
    String getLessonForm(Model model) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("teachers", teacherRepository.findAll());
        return "lesson-form";
    }

    @PostMapping("/lesson/add")
    String postLessonForm(@Valid Lesson lesson, BindingResult result) {
        Student student = studentRepository.findById(lesson.getSelectedStudentId()).get();
        Teacher teacher = teacherRepository.findById(lesson.getSelectedTeacherId()).get();

        lesson.setTeacher(teacher);
        lesson.setStudent(student);

        if (result.hasErrors()) {
            return "lesson-form";
        } else {
            lesson = this.lessonRepository.save(lesson);
            return "redirect:/api/lessons/" + lesson.getId();
        }
    }
}
