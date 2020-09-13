package com.thoughtworks.capability.gtb.entrancequiz.Service;

import com.thoughtworks.capability.gtb.entrancequiz.Domain.Group;
import com.thoughtworks.capability.gtb.entrancequiz.Domain.Student;
import com.thoughtworks.capability.gtb.entrancequiz.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }

    public List<Group> getGroups() {
        List<Student> student = getStudents();
        Collections.shuffle(student);
        int stuSize = student.size();
        int groupNum = 6;
        int initGroup = stuSize/groupNum;
        int restStudents = 0;
        if(initGroup*groupNum < stuSize) {
            restStudents = stuSize - initGroup*groupNum;
        }
        List<Group> groups = new ArrayList<>();
        int total=0;
        for(int i=0; i<groupNum; i++) {
            List<Student> mid = new ArrayList<>();
            if(restStudents > 0) {
                for (int j = 0; j < initGroup + 1; j++) {
                    mid.add(student.get(total));
                    total++;
                }
            } else{
                for (int j = 0; j < initGroup ; j++) {
                    mid.add(student.get(total));
                    total++;
                }

            }
            Group tmp = new Group("Group" + (i + 1), mid);
            groups.add(tmp);
            restStudents = restStudents - 1;
        }
        return groups;
    }

    public void addStudent(String name) {
        int studentNum = studentRepository.getStudentCount();
        Student student = new Student(studentNum + 1, name);
        studentRepository.add(student);

    }


    public void changeGroupName(String oldName, String newName) {
        studentRepository.changeGroupName(oldName, newName);
    }

}
