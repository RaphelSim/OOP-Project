package Accounts;

import Common.*;

public class Account implements DatabaseItems {
    private String name;
    private String id;
    private String password;
    private String email;
    private String phone;
    private String dob;
    private Role role;

    public Account(String name, String id, Role role, String dob, String email, String phone) {
        this.name = name;
        this.id = id;
        this.password = "password";
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.dob = dob;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getdob() {
        return dob;
    }

    public String getid() {
        return id;
    }

    public String getpassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Role getrole() {
        return role;
    }

    // setters
    public void setPassword(String pwd) {
        this.password = pwd;
    }

    public void setEmail(String pwd) {
        this.password = pwd;
    }

    public void setPhone(String pwd) {
        this.password = pwd;
    }
}
