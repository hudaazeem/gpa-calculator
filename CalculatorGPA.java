//plan:
//first prompts user to enter course information (how many aps, what grades)
//main features:
//asks if user wants cumultive gpa (98.4)
//asks if user wants unweighted gpa (3.9)
//asks if user wants weighted gpa (4.3)
//asks if user wants rank gpa (special criteria)
//if i want xx to increase by xx, what should i do (??)
//go back to home screen after an option instead of displaying menu options instead
//TODO - add error statements to prevent dividion by zero


//Projects/Performance Tasks = 20%: This aligns with your original question — at many DISD high schools, big projects or performance-based tasks generally count about 20% of the grading period.
//Daily Grades = 40%: Homework, participation, quizzes — frequent small assignments.
//Quarter Tests = 15%: Sometimes lumped in with tests or counted as a separate “big test” at the end of the quarter.
//Tests = 25%: The typical exams or chapter tests.
    



import java.util.Scanner;

public class CalculatorGPA{
    public static void main(String[] args) {
        //TODO - maybe make a cool animation?
        Scanner scanner = new Scanner(System.in);

        boolean running = true; 

        System.out.println("Welcome to my project!");
        System.out.println("This program will calculate all of your GPAs based on the Dallas ISD system.");
            
        while(running){
            
            System.out.println("Select any of the options below!");
            System.out.println("[1] Calculate class grade");
            System.out.println("[2] Calculate cumulative GPA");
            System.out.println("[3] Calculate unweighted GPA");
            System.out.println("[4] Calculate weighted GPA");
            System.out.println("[5] Calculate ranking GPA");
            System.out.println("[6] Exit");
            System.out.println("[0] Info");
            double average;
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    average = calculateClassGrade(scanner);
                    System.out.println("Your grade for the class is " + average + "!");
                    break;
                case 2: 
                    average = calculateCumulativeGPA(scanner);
                    System.out.println("Your cumulative GPA is " + average + "!");
                    break;
                case 3:
                    average = calculateCumulativeGPA(scanner);
                    double averageUnweighted = average/25;
                    System.out.println("Your unweighted GPA is " + averageUnweighted+ "!");
                    break;
                case 4:
                    average = calculateWeightedGPA(scanner);
                    System.out.println("Your weighted GPA is " + average + "!");
                    break;
                case 5:
                    //estimate future grades
                    break;
                case 6:
                    running = false;
                    System.out.println("Bye!!");
                    break;
                case 0:
                    System.out.println("[put instructions here]");
                    //scanner option to go back to home screen
                    break;
                default:
                    System.out.println("Invalid choice, please try again!");
            }
        }    
    }
        
    public static double[] receiveGrades(Scanner scanner, String courseType){
        System.out.println("Please input the number of " + courseType +" classes you are currently taking AND have taken.");
            int numCourses = scanner.nextInt();

            double[] grades = new double[numCourses];

            for(int i=0; i<numCourses; i++){
                System.out.println("What is your raw score (out of 100) for " +"course " + (i+1) + "?");
                double grade = scanner.nextDouble();
                
                grades[i]=grade;
                
            }
            return grades;     
    }

    public static double calculateCumulativeGPA(Scanner scanner){
       double[] grades = receiveGrades(scanner, "all");
        double sum = 0.0;
        for(int i = 0; i< grades.length; i++){
            sum += grades[i];
        }
        double average = sum/grades.length;
        return average;
    }

    
    public static double calculateWeightedGPA(Scanner scanner){
        double[] gradesAP = receiveGrades(scanner, "Category 1");
        for(int i =0; i< gradesAP.length; i++){
            if(gradesAP[i] >= 97){
                gradesAP[i] = 5;
            }else if(gradesAP[i]>=93){
                gradesAP[i] = 4.8;
            }else if(gradesAP[i]>=90){
                gradesAP[i]=4.6;
            }else if(gradesAP[i]>=87){
                gradesAP[i] = 4.4;
            }else if(gradesAP[i]>=83){
                gradesAP[i]=4.2;
            }else if(gradesAP[i]>=80){
                gradesAP[i]=4.0;
            }else if(gradesAP[i]>=77){
                gradesAP[i]=3.8;
            }else if(gradesAP[i]>=73){
                gradesAP[i]=3.6;
            }else if(gradesAP[i]>=71){
                gradesAP[i]=3.4;
            }else if(gradesAP[i]>=70){
                gradesAP[i]=3.0;
            }else if(gradesAP[i]>=60){
                gradesAP[i]=2.0;
            }else{
                gradesAP[i]=0;
            }
        }
        double sumAP=0.0;
        for(int i = 0; i<gradesAP.length; i++){
            sumAP+=gradesAP[i];
        }

        double[] gradesHonors = receiveGrades(scanner, "Category 2");
        for(int i = 0; i< gradesHonors.length; i++){
            if(gradesHonors[i] >= 97){
                gradesHonors[i] = 4.5;
            }else if(gradesHonors[i]>=93){
                gradesHonors[i] = 4.3;
            }else if(gradesHonors[i]>=90){
                gradesHonors[i]=4.1;
            }else if(gradesHonors[i]>=87){
                gradesHonors[i] = 3.9;
            }else if(gradesHonors[i]>=83){
                gradesHonors[i]=3.7;
            }else if(gradesHonors[i]>=80){
                gradesHonors[i]=3.5;
            }else if(gradesHonors[i]>=77){
                gradesHonors[i]=3.3;
            }else if(gradesHonors[i]>=73){
                gradesHonors[i]=3.1;
            }else if(gradesHonors[i]>=71){
                gradesHonors[i]=2.9;
            }else if(gradesHonors[i]>=70){
                gradesHonors[i]=2.5;
            }else if(gradesHonors[i]>=60){
                gradesHonors[i]=1.5;
            }else{
                gradesHonors[i]=0;
            }
        }

        double sumHonors = 0.0;
        for(int i=0; i<gradesHonors.length; i++){
            sumHonors+=gradesHonors[i];
        }

        double[] gradesRegular = receiveGrades(scanner, "Category 3");
        for(int i = 0; i<gradesRegular.length; i++){
            if(gradesRegular[i] >= 97){
                gradesRegular[i] = 4.0;
            }else if(gradesRegular[i]>=93){
                gradesRegular[i] = 3.8;
            }else if(gradesRegular[i]>=90){
                gradesRegular[i]= 3.6;
            }else if(gradesRegular[i]>=87){
                gradesRegular[i] = 3.4;
            }else if(gradesRegular[i]>=83){
                gradesRegular[i]=3.2;
            }else if(gradesRegular[i]>=80){
                gradesRegular[i]=3.0;
            }else if(gradesRegular[i]>=77){
                gradesRegular[i]=2.8;
            }else if(gradesRegular[i]>=73){
                gradesRegular[i]=2.6;
            }else if(gradesRegular[i]>=71){
                gradesRegular[i]=2.4;
            }else if(gradesRegular[i]>=70){
                gradesRegular[i]=2.0;
            }else if(gradesRegular[i]>=60){
                gradesRegular[i]=1.0;
            }else{
                gradesRegular[i]=0;
            }
        }

        double sumRegular = 0.0;
        for(int i=0; i<gradesRegular.length; i++){
            sumRegular+=gradesRegular[i];
        }
        double average = (sumAP+sumHonors+sumRegular)/(gradesAP.length+gradesHonors.length+gradesRegular.length);
        return average;
    }

    public static double calculateRankingGPA(Scanner scanner){
        
        return 6.7;
    }

    public static double calculateClassGrade(Scanner scanner){
        double dailyAverage = calculateClassGradeCategory(scanner, "daily grades");
        double projectAverage = calculateClassGradeCategory(scanner, "project grades");
        double quarterTestAverage = calculateClassGradeCategory(scanner, "quarter tests");
        double testAverage = calculateClassGradeCategory(scanner, "regular tests");
        double acpAverage = calculateClassGradeCategory(scanner, "ACP/midterms/finals");

        double semesterGrade = (0.2*projectAverage)+(0.4*dailyAverage)+(.15*quarterTestAverage)+(.25*testAverage);

        double finalSemesterGrade = 0.85*semesterGrade+0.15*acpAverage;

        return finalSemesterGrade;
    }


    public static double calculateClassGradeCategory(Scanner scanner, String category){
        System.out.println("Enter the number of " + category + " you have.");
        int numCourses = scanner.nextInt();
        double[] grades = new double[numCourses];
        for(int i =0; i< numCourses; i++){
            System.out.println("Enter the grade for assignment " + (i+1));
            grades[i]=scanner.nextDouble();
        }

        double sum = 0.0;
        for(int i =0; i<grades.length; i++){
            sum += grades[i];
        }

        double average = sum/numCourses;

        return average;
    }
}