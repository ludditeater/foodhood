package com.creative.foodhood.model;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("user")
public class UserEntity {

    @PrimaryKey
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String userrolename;
    private String activationcode;
    private String activationStatus;

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUserrolename() {
        return userrolename;
    }

    public void setUserrolename(String userrole) {
        this.userrolename = userrole;
    }

    
    
    @Override
    public String toString() {
        return "User username = " + username + " firstname :: " + firstname + " lastname :: " + lastname
                + " userrole :: " + userrolename;
    }

    public String getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(String activationStatus) {
        this.activationStatus = activationStatus;
    }

	public String getActivationcode() {
		return activationcode;
	}

	public void setActivationcode(String activationcode) {
		this.activationcode = activationcode;
	}


}
