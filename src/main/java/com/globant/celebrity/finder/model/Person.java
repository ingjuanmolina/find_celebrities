package com.globant.celebrity.finder.model;

public class Person {

    private int id;

    public Person(){}

    public Person(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Person)){
            return false;
        }
        return this.id == ((Person) obj).getId();
    }

    @Override
    public String toString() {
        return String.format("Person id: %d", id);
    }
}
