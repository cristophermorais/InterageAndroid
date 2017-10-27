package com.interage.app.DTO;

/**
 * Created by alexa on 10/27/2017.
 */

public class EmailDTO {
    private String Email;

    public EmailDTO(String email) {
        this.Email = email;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
