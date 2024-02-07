package ru.course.spring.models;

public class Book {
    private int id;
    private String name;
    private int person_id;

    public Book(){}
    public Book(int id, String name, int person_id) {
        this.id = id;
        this.name = name;
        this.person_id = person_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
}
