package UI;

import Common.UserInterface;

public class LoginPage extends UserInterface{

    public static String getUserId(){
        System.out.println("Please enter your user id");
        return scanner.nextLine();
    }

    public static String getPassword(){
        System.out.println("Please enter your password");
        return scanner.nextLine();
    }
}
