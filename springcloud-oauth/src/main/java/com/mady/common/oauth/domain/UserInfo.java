package com.mady.common.oauth.domain;

import lombok.Data;
import java.io.Serializable;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/15 13:52
 * @description
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -2872542873975484040L;

    private String username;

    private String password;

    private String roleId;


    public UserInfo() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
