package com.saleskit.cbbank.features.account;

public class LoginModel {

    public LoginModel(String username, String password) {
        Username = username;
        Password = password;
    }

    /**
     * Username : tuyetdtb
     * Password : 123456
     */

    private String Username;
    private String Password;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
