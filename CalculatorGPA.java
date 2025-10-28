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
        //TODO - maybe make a cool animation?
        Scanner scanner = new Scanner(System.in);

        boolean running = true; 

        
            
        while(running){
            System.out.println("Welcome to my project!");
            System.out.println("This program will calculate all of your GPAs based on the Dallas ISD system.");
            System.out.println("Select any of the options below!");
            System.out.println("[1] Calculate cumulative GPA.");
            System.out.println("[2] Calculate unweighted GPA.");
            System.out.println("[3] Calculate weighted GPA");
            System.out.println("[4] Placeholder for estimations");

            int choice = scanner.nextInt();

            switch(choice){
                case 1: 
                    //calculateCumulativeGPA();
                    break;
                case 2:
                    //calculateUnweightedGPA();
                    break;
                case 3:
                    //calculateWeightedGPA();
                    break;
                default:
                    System.out.println("Invalid choice, please try again!");
            }
        }
        //double apGrades[]=receiveGrades(scanner, "AP");
        //double honorsGrades[]=receiveGrades(scanner, "honors");
        //double onLevelGrades[]=receiveGrades(scanner, "on level");

        
        
    }
        
    public static double[] receiveGrades(Scanner scanner, String courseType){
        System.out.println("Please input the number of " + courseType +" classes you are currently taking or have taken.");
            int numCourses = scanner.nextInt();

            double[] grades = new double[numCourses];

            for(int i=1; i<=numCourses; i++){
                System.out.println("What is your raw score (out of 100) for " + courseType +"course" + i + "?");
                double grade = scanner.nextDouble();
                
                grades[i-1]=grade;
                
            }
            return grades;     
    }

    public static double calculateCumulativeGPA(){
        receiveGrades(scanner, "all");
        
    }
}