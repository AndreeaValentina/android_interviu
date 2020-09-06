package com.example.andreeaproblem;

public class UserListResponse {

    // POJO class to get the data from web api
    private String first_name;
    private String address;

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String email) { this.address = address; }

    public UserListResponse() {
        super();
    }
}
