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

    public Account(String name, String id, String password, Role role) {
        this(name,id,role);
        this.password = password;
    }


    //call the deserialisation method
    public Account(String ...params){
        deserialise(params);
    }

    //interface functions
    public void deserialise(String ...params){
        //Name,id,Password,Role
        Role role = Role.fromString(params[3]);
        this.name = params[0];
        this.id = params[1];
        this.password = params[2];
        this.role = role;
    }

    public String serialise(){
        return String.format("%s,%s,%s,%s\n",
        name,
        id,
        password,
        role.toString());
    }

    public void printItem(){
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Role: " + role);
            System.out.println("Password: " + password);
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
