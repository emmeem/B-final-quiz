package com.thoughtworks.capability.gtb.entrancequiz.Controller;

import com.thoughtworks.capability.gtb.entrancequiz.Domain.Group;
import com.thoughtworks.capability.gtb.entrancequiz.Domain.Student;
import org.springframework.web.bind.annotation.*;
import com.thoughtworks.capability.gtb.entrancequiz.Utils.StudentList;

import java.util.List;

@RestController
public class StudentController {

    private  final  StudentList studentList;

    public StudentController(StudentList studentList) {
        this.studentList = studentList;
    }

    @GetMapping("/students")
    @CrossOrigin
    public List<Student> getStudentList() {
        return studentList.initStudentList();
    }

    @GetMapping("/groups")
    @CrossOrigin
    public List<Group> getGroups() {
        return studentList.getStudentGroups();
    }

    @PostMapping("/student/{studentName}")
    @CrossOrigin
    public void addStudent(@PathVariable("studentName") String studentName) {
        studentList.addStudent(studentName);
    }

    @PutMapping("/team/{teamName}")
    @CrossOrigin
    public void changeTeamName(@PathVariable("studentName") String teamName) {
        studentList.changeTeamName(teamName);
    }

}
