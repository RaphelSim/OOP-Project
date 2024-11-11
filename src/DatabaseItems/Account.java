package DatabaseItems;

import Common.*;

public class Account implements DatabaseItems {
    private String name;
    private String id;
    private String password;
    private Role role;
    private Gender gender;
    private int age;
    
    public Account(String name, String id, Role role, Gender gender, int age) {
        this.name = name;
        this.id = id;
        this.password = "password";
        this.role = role;
        this.gender = gender;
        this.age = age;
    }

    public Account(String name, String id, String password, Role role, Gender gender, int age) {
        this(name,id,role,gender,age);
        this.password = password;
    }

    //call the deserialisation method
    public Account(String ...params){
        deserialise(params);
    }

    //interface functions
    public void deserialise(String ...params){
        //name,id,password,role,gender,age
        this.name = params[0];
        this.id = params[1];
        this.password = params[2];
        this.role = Role.fromString(params[3]);
        this.gender = Gender.fromString(params[4]);
        this.age = Integer.parseInt(params[5]);
    }

    public String serialise(){
        return String.format("%s,%s,%s,%s,%s,%s\n",
        name,
        id,
        password,
        role.toString(),
        gender.toString(),
        Integer.toString(age)
        );
    }

    public void printItem(){
            System.out.println(); // add a line break to improve readability
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Role: " + role);
            System.out.println("Password: " + password);
            System.out.println("Gender: " + gender.toString());
            System.out.println("Age: " + age);
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

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    // setters
    
    public void setName(String name) {
        this.name = name;
    }

    // Setter for id
    public void setId(String id) {
        this.id = id;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Setter for role
    public void setRole(Role role) {
        this.role = role;
    }

    // Setter for gender
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    // Setter for age
    public void setAge(int age) {
        this.age = age;
    }

    // utilities functions
    public boolean checkPassword(String password){
        if(this.password.equals(password)) return true;
        else return  false;
    }
}
