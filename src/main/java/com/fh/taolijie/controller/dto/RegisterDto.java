package com.fh.taolijie.controller.dto;

import com.fh.taolijie.utils.Constants;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by wynfrith on 15-3-26.
 */
public class RegisterDto {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    @NotEmpty(message = Constants.ErrorType.USERNAME_ILLEGAL)
    @Length(min = 6,max =25,message = Constants.ErrorType.USERNAME_ILLEGAL)
    private String username;

    @NotEmpty(message = Constants.ErrorType.PASSWORD_ILLEGAL)
    @Length(min = 6,max =25,message = Constants.ErrorType.PASSWORD_ILLEGAL)
    private String password;

    private String email;

    @NotEmpty(message = Constants.ErrorType.PASSWORD_ILLEGAL)
    private String repassword;
}