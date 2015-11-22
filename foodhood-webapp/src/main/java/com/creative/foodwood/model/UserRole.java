package com.creative.foodwood.model;

import java.util.List;

public class UserRole {
    private String userRoleName;
    private List<String> userPermissions;

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public List<String> getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(List<String> userPermissions) {
        this.userPermissions = userPermissions;
    }

}
