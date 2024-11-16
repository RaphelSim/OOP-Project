package DatabaseItems;

import Common.*;

/**
 * Represents an account in the system.
 * This class implements the DatabaseItems interface and provides
 * functionalities to manage user account details such as name, ID,
 * password, role, gender, and age.
 */
public class Account implements DatabaseItems {
    private String name;
    private String id;
    private String password;
    private Role role;
    private Gender gender;
    private int age;

    /**
     * Constructs an Account with the specified details.
     *
     * @param name   the name of the account holder
     * @param id     the unique identifier for the account
     * @param role   the role of the account holder (e.g., ADMIN, USER)
     * @param gender the gender of the account holder
     * @param age    the age of the account holder
     */
    public Account(String name, String id, Role role, Gender gender, int age) {
        this.name = name;
        this.id = id;
        this.password = "password"; // Default password
        this.role = role;
        this.gender = gender;
        this.age = age;
    }

    /**
     * Constructs an Account with specified details including a custom password.
     *
     * @param name     the name of the account holder
     * @param id       the unique identifier for the account
     * @param password the password for the account
     * @param role     the role of the account holder (e.g., ADMIN, USER)
     * @param gender   the gender of the account holder
     * @param age      the age of the account holder
     */
    public Account(String name, String id, String password, Role role, Gender gender, int age) {
        this(name,id,role,gender,age);
        this.password = password;
    }

    /**
     * Constructs an Account by deserializing from a set of parameters.
     *
     * @param params an array of strings containing account details in order:
     *               name, id, password, role, gender, age
     */
    public Account(String ...params){
        deserialise(params);
    }

    /**
     * Deserializes account details from a string array.
     *
     * @param params an array of strings containing account details in order:
     *               name, id, password, role, gender, age
     */
    public void deserialise(String ...params){
        //name,id,password,role,gender,age
        this.name = params[0];
        this.id = params[1];
        this.password = params[2];
        this.role = Role.fromString(params[3]);
        this.gender = Gender.fromString(params[4]);
        this.age = Integer.parseInt(params[5]);
    }

    /**
     * Serializes account details into a comma-separated string.
     *
     * @return a string representation of the account details
     */
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

    /**
     * Prints account details to standard output.
     */
    public void printItem(){
        System.out.println(); // Add a line break to improve readability
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Role: " + role);
        System.out.println("Password: " + password);
        System.out.println("Gender: " + gender.toString());
        System.out.println("Age: " + age);
    }

    // Getters

    /**
     * Returns the name of the account holder.
     *
     * @return the name of the account holder
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the unique identifier for the account.
     *
     * @return the unique identifier for the account
     */
    public String getid() {
        return id;
    }

    /**
     * Returns the password for the account.
     *
     * @return the password for the account
     */
    public String getpassword() {
        return password;
    }

    /**
     * Returns the role of the account holder.
     *
     * @return the role of the account holder
     */
    public Role getrole() {
        return role;
    }

    /**
     * Returns the gender of the account holder.
     *
     * @return the gender of the account holder
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Returns the age of the account holder.
     *
     * @return the age of the account holder
     */
    public int getAge() {
        return age;
    }

    // Setters

    /**
     * Sets a new name for the account holder.
     *
     * @param name new name for the account holder
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets a new unique identifier for this account.
     *
     * @param id new unique identifier for this account
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets a new password for this account.
     *
     * @param password new password for this account
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets a new role for this account.
     
      *
      *@param role new role for this account 
      */ 
      public void setRole(Role role) { 
          this.role = role; 
      } 

      /** 
       * Sets a new gender for this account. 
       *
       *@param gender new gender for this account 
       */ 
       public void setGender(Gender gender) { 
           this.gender = gender; 
       } 

       /** 
       * Sets a new age for this account. 
       *
       *@param age new age for this account 
       */ 
       public void setAge(int age) { 
           this.age = age; 
       } 

       /** 
       * Checks if a given password matches this account's stored password. 
       *
       *@param password The password to check against. 
       *@return true if passwords match; false otherwise. 
       */ 
      public boolean checkPassword(String password){ 
          return this.password.equals(password); 
      } 
}