package com.uade.tpo.marketplace.dto.request;

import com.uade.tpo.marketplace.model.Role;

public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
