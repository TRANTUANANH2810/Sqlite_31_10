package com.example.csdl_sqlite.model;

public class  Employee {
    private String id, name ;
    private float salary;

    public Employee(){

    }


    public Employee(String id, String name, float salary) {
        this.id = id;
        this.name = name;
        this.salary = salary ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getsalary() {
        return salary;
    }

    public void setsalary(float diem) {
        this.salary = diem;
    }
}
