package com.example.techthink.controller.user.model.response;

import java.util.List;
import java.util.Objects;

public class UserResponse {
    private Long id;
    private String first_name;
    private String last_name;
    private String username;
    private String email;
    private String description;
    private String profilePictureURL;
    private List<String> roleName;

    public UserResponse(Long id, String first_name, String last_name, String username, String email, String description, String profilePictureURL, List<String> roleName) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.email = email;
        this.description = description;
        this.profilePictureURL = profilePictureURL;
        this.roleName = roleName;

    }


    public UserResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public List<String> getRoleName() {
        return roleName;
    }

    public void setRoleName(List<String> roleName) {
        this.roleName = roleName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(first_name, that.first_name) && Objects.equals(last_name, that.last_name) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(description, that.description) && Objects.equals(profilePictureURL, that.profilePictureURL) && Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, username, email, description, profilePictureURL, roleName);
    }
}
