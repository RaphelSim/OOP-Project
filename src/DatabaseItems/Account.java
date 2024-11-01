package DatabaseItems;

import Common.*;

public class Account implements DatabaseItems {
    private String name;
    private String id;
    private String password;
    private Role role;

    public Account(String name, String id, Role role) {
        this.name = name;
        this.id = id;
        this.password = "password";
        this.role = role;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getid() {
        return id;
    }

    public String getpassword() {
        return password;
    }

    public Role getrole() {
        return role;
    }

    // setters
    public void setPassword(String pwd) {
        this.password = pwd;
    }
}
