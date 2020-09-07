package com.thoughtworks.capability.gtb.entrancequiz.Controller;

import com.thoughtworks.capability.gtb.entrancequiz.Domain.Group;
import com.thoughtworks.capability.gtb.entrancequiz.Domain.Student;
import org.springframework.web.bind.annotation.*;
import com.thoughtworks.capability.gtb.entrancequiz.Utils.StudentList;

import java.util.List;

@RestController
public class StudentController {

    @GetMapping("/students")
    @CrossOrigin
    public List<Student> getStudentList() {
        StudentList initStudentList = new StudentList();
        return initStudentList.initStudentList();
    }

    @GetMapping("/groups")
    @CrossOrigin
    public List<Group> getGroups() {
        StudentList groups = new StudentList();
        return groups.getStudentGroups();
    }

    @PostMapping("/student/{studentName}")
    @CrossOrigin
    public void addStudent(@PathVariable("studentName") String studentName) {
        StudentList studentList = new StudentList();
        studentList.addStudent(studentName);
    }

}
