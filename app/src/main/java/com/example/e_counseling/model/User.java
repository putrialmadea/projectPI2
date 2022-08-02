package com.example.e_counseling.model;

public class User {
    private String id, input_jurusanadmin, input_kampusadmin;

    public User(){

    }

    public User(String input_jurusanadmin, String input_kampusadmin){
        this.input_jurusanadmin = input_jurusanadmin;
        this.input_kampusadmin = input_kampusadmin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInput_jurusanadmin() {
        return input_jurusanadmin;
    }

    public void setInput_jurusanadmin(String input_jurusanadmin) {
        this.input_jurusanadmin = input_jurusanadmin;
    }

    public String getInput_kampusadmin() {
        return input_kampusadmin;
    }

    public void setInput_kampusadmin(String input_kampusadmin) {
        this.input_kampusadmin = input_kampusadmin;
    }
}
