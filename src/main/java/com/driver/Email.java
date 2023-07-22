package com.driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public boolean changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if (!this.password.equals(oldPassword)) {
            return false;
        }

        // Check if the new password meets all the criteria using regular expressions
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(newPassword);

        if (matcher.matches()) {
            this.password = newPassword;
            return true;
        } else {
            return false;
        }
    }
}

