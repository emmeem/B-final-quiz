package com.thoughtworks.capability.gtb.entrancequiz.Controller;

import com.thoughtworks.capability.gtb.entrancequiz.Domain.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thoughtworks.capability.gtb.entrancequiz.Utils.InitStudentList;
import java.util.List;

@RestController
public class StudentController {

    @GetMapping("/students")
    public List<Student> getStudentList() {
        InitStudentList initStudentList = new InitStudentList();
        return initStudentList.initStudent();
    }
}
