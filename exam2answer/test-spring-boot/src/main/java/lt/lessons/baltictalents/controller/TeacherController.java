package lt.lessons.baltictalents.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lt.lessons.baltictalents.model.Teacher;
import lt.lessons.baltictalents.repository.TeacherRepository;

@Controller
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/teacher/new")
    String getStudentForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher-form";
    }

    @PostMapping("/teacher/add")
    String postLessonForm(@Valid Teacher teacher, BindingResult result) {
        if (result.hasErrors()) {
            return "teacher-form";
        } else {
            teacher = this.teacherRepository.save(teacher);
            return "redirect:/api/teachers/" + teacher.getId();
        }
    }
}
