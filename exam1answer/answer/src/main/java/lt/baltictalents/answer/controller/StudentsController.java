package lt.baltictalents.answer.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.val;
import lt.baltictalents.answer.model.Student;
import lt.baltictalents.answer.repository.StudentsRepository;

@RestController
@RequestMapping("/api")
public class StudentsController {
    @Autowired
    StudentsRepository studentsRepository;

    @GetMapping(value = "/students")
    public List<Student> getStudents(
        @RequestParam(value = "sort", defaultValue = "lecture,course") String[] sort
    ) {
        System.out.println(Arrays.toString(sort));
        val students = studentsRepository.findAll();

        // students.sort((left, right) -> {
        //     val courseCompare = left.getCourse().compareTo(right.getCourse());
        //     if(courseCompare != 0) {
        //         return courseCompare;
        //     }

        //     return left.getLecture().compareTo(right.getLecture());
        // });

        // Collections.sort(students, (left, right) -> {
        //     val courseCompare = left.getCourse().compareTo(right.getCourse());
        //     if (courseCompare != 0) {
        //         return courseCompare;
        //     }

        //     return left.getLecture().compareTo(right.getLecture());
        // });
        
        students.sort((left, right)-> {
            return Comparator.
                comparing(Student::getCourse).
                thenComparing(Student::getLecture).
                compare(left, right);
        });
        
        return students;
    }
}