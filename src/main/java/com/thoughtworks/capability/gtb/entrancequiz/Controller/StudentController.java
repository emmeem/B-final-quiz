package com.thoughtworks.capability.gtb.entrancequiz.Controller;

import com.thoughtworks.capability.gtb.entrancequiz.Domain.Group;
import com.thoughtworks.capability.gtb.entrancequiz.Domain.Student;
import org.springframework.web.bind.annotation.*;
import com.thoughtworks.capability.gtb.entrancequiz.Service.StudentService;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    @CrossOrigin
    public List<Student> getStudentList() {
        return studentService.getStudents();
    }

    @GetMapping("/groups")
    @CrossOrigin
    public List<Group> getGroups() {
        return studentService.getGroups();
    }


    @PostMapping("/students")
    @CrossOrigin
    public void addStudent(@RequestBody String name) {
        studentService.addStudent(name);
    }


    @PutMapping("/groups/{oldName}")
    @CrossOrigin
    public void changeGroupName(@PathVariable String oldName, @RequestBody String newName) {
        studentService.changeGroupName(oldName, newName);
    }

}
