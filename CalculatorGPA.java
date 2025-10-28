//plan:
//first prompts user to enter course information (how many aps, what grades)
//main features:
//asks if user wants cumultive gpa (98.4)
//asks if user wants unweighted gpa (3.9)
//asks if user wants weighted gpa (4.3)
//asks if user wants rank gpa (special criteria)
//if i want xx to increase by xx, what should i do (??)
import java.util.Scanner;

public class CalculatorGPA{
    public static void main(String[] args) {
        //TODO - maybe make a cool animation??
        System.out.println("Welcome to my project!");
        System.out.println("This program will calculate all of your GPAs based on the Dallas ISD system.");
        
        System.out.println("Please input the number of AP courses you are taking.");
        Scanner scannerAP = new Scanner(System.in);
        int numAPs = scannerAP.nextInt();

        for(int i=1; i<=numAPs; i++){
            System.out.println("What is your raw score out of hundred for AP course " + i + "?");
            Scanner scannerAPgrade = new Scanner(System.in);
            int APgrade = scannerAPgrade.nextInt();
            
        }        
        
        System.out.println("Select any of the options below!");

        
    }
        

}