package com.thoughtworks.capability.gtb.entrancequiz.Utils;

import com.thoughtworks.capability.gtb.entrancequiz.Domain.Group;
import com.thoughtworks.capability.gtb.entrancequiz.Domain.Student;

import java.util.*;

public class StudentList {
    String[] studentName = {"沈乐棋","徐慧慧","陈思聪","王江林","王登宇","杨思雨","江雨舟","廖燊","胡晓","但杰","盖迈达",
            "肖美琦","黄云洁","齐瑾浩","刘亮亮","肖逸凡","王作文",
            "郭瑞凌", "李明豪", "党泽", "肖伊佐", "贠晨曦", "李康宁", "马庆", "商婕", "余榕",
            "谌哲", "董翔锐", "陈泰宇", "赵允齐", "张柯", "廖文强", "刘轲", "廖浚斌", "凌凤仪"};
    public List<Student> initStudentList() {
        List<Student> studentList = new ArrayList<>();
        for(int i=0; i<35; i++) {
            Student s1 = new Student();
            s1.setId(i+1);
            s1.setStudentName(studentName[i]);
            studentList.add(s1);
        }
        return studentList;
    }

    public List<Group> getStudentGroups() {
        List<Student> student = initStudentList();
        Collections.shuffle(student);
        int stuSize = student.size();
        int initGroup = stuSize/6;
        int restStudents = 0;
        if(initGroup*6 < stuSize) {
            restStudents = stuSize - initGroup*6;
        }
        List<Group> studentGroup = new ArrayList<>();
        for(int i=0; i<6; i++) {
            List<Student> mid = new ArrayList<>();
            if(restStudents > 0) {
                for (int j = 0; j < initGroup + 1; j++) {
                    mid.add(student.get(i * initGroup + j));
                }
                Group tmp = new Group("Group" + (i + 1), mid);
                studentGroup.add(tmp);
            } else{
                for (int j = 0; j < initGroup ; j++) {
                    mid.add(student.get(i * initGroup + j));
                }
                Group tmp = new Group("Group" + (i + 1), mid);
                studentGroup.add(tmp);
            }
            restStudents = restStudents - 1;
        }
        return studentGroup;
    }

    public void addStudent(String name) {
        List<Student> student = initStudentList();
        int stuIndex = studentName.length +1 ;
        studentName[stuIndex] = name;
    }


}
