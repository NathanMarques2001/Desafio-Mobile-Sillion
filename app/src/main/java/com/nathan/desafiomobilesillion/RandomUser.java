package com.nathan.desafiomobilesillion;

public class RandomUser {
    private String username, name, age, email, userImageURL;

    public RandomUser(String username, String name, String age, String email, String userImageURL) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.email = email;
        this.userImageURL = userImageURL;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

}
