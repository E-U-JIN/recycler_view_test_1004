package com.example.recycler_view_test_1004;

//유저 정보를 가지고 있는 객체 만들기위함?
public class User {
    private String profile;
    private String id;
    private String pw;
    private String userName;
    public User(){}

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
