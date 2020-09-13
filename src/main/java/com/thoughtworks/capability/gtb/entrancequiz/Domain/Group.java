package com.thoughtworks.capability.gtb.entrancequiz.Domain;

import java.util.List;

public class Group {
    private String name;
    List<Student> students;

    public Group(String groupName, List<Student> students){
        this.name = name;
        this.students = students;
    }

    public String getGroupName() {
        return name;
    }

    public void setGroupName(String groupName) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
