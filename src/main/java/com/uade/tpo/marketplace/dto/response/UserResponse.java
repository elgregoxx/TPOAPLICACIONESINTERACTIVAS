package com.uade.tpo.marketplace.dto.response;

import com.uade.tpo.marketplace.model.Role;
import com.uade.tpo.marketplace.model.User;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;

    public static UserResponse from(User user) {
        UserResponse r = new UserResponse();
        r.id = user.getId();
        r.name = user.getName();
        r.email = user.getEmail();
        r.role = user.getRole();
        return r;
    }
}
