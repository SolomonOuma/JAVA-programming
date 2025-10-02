import java.util.Scanner;

public class melogin {
    public static void main(String[] args){
        String correctUsername = "Jon Snow";
        String correctPassword = "r1dc4x";
        
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
                
        Scanner log = new Scanner(System.in);//you only require one scanner object
        
        while (attempts < MAX_ATTEMPTS){
            System.out.println("Enter username: ");
            String inputUsername = log.nextLine();
        
            System.out.println("Enter password: ");
            String inputPassword = log.nextLine();
        
        
            if (inputUsername.equals(correctUsername) && inputPassword.equals(correctPassword)){
            System.out.println("Successful login.");
            break;//to allow smooth exit and avoid recurring prompts
            }
            else if (!inputUsername.equals(correctUsername) && inputPassword.equals(correctPassword)){
            System.out.println("Wrong username.");
            }
            else if (inputUsername.equals(correctUsername) && !inputPassword.equals(correctPassword)){
            System.out.println("Wrong password.");
            }
            else{
            System.out.println("Failed Login.");
            }
            attempts++;

            if (attempts == MAX_ATTEMPTS) {
                System.out.println("Maximum attempts reached. Access denied.");
            }   
        }
        log.close();
        }
    }

