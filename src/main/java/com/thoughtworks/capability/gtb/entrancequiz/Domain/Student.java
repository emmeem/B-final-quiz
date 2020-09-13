package com.thoughtworks.capability.gtb.entrancequiz.Domain;

public class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return name;
    }

    public void setStudentName(String studentName) {
        this.name = name;
    }

}
