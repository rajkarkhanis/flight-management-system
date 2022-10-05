package com.flights.dto;


import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDto {

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @NotEmpty(message = "userType cannot be empty")
    private String userType;
    @NotEmpty(message = "userName cannot be empty")
    @Size(min=3,message = "username must have minimum 3 characters")
    private String userName;
    @NotEmpty(message = "password cannot be empty")
    private String userPassword;
    @NotEmpty(message = "Phone number cannot be empty")
    @Size(min=10,max=10,message = "phone number must have 10 digits")
    private String userPhone;
    @NotEmpty
    @Email(regexp = "[a-zA-Z_][\\w]*@[a-zA-Z]+[.][a-zA-Z]+",message = "Invalid Email, should follow <id>@<domain>.<topleveldomain> pattern")
    private String userEmail;
    public UserDto(String userType, String userName, String userPassword, String userPhone, String userEmail) {
        this.userType = userType;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }
public UserDto(){}


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


}
