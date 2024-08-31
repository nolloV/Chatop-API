package com.openclassrooms.chatop.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class LoginUserDto {

    private String email;
    private String password;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
