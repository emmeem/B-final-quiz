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
        StudentList studentList = new StudentList();
        return studentList.initStudentList();
    }

    @GetMapping("/groups")
    @CrossOrigin
    public List<Group> getGroups() {
        StudentList studentList = new StudentList();
        return studentList.getStudentGroups();
    }


    @PostMapping("/student/{studentName}")
    @CrossOrigin
    public void addStudent(@PathVariable("studentName") String studentName) {
        StudentList studentList = new StudentList();
        studentList.addStudent(studentName);
    }


    @PutMapping("/team/{teamName}")
    @CrossOrigin
    public void changeTeamName(@PathVariable("studentName") String teamName) {
        StudentList studentList = new StudentList();
        studentList.changeTeamName(teamName);
    }

}
