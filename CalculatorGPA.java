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
                    //ranking gpa
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
        double[] gradesHonors = receiveGrades(scanner, "Category 2");
        double[] gradesRegular = receiveGrades(scanner, "Category 3");
        
        for(int i =0; i< gradesAP.length; i++){
            gradesAP[i] = convertToGPA(gradesAP[i], "AP");
        }

        for(int i = 0; i< gradesHonors.length; i++){
            gradesHonors[i] = convertToGPA(gradesHonors[i], "Honors");
        }

        for(int i = 0; i<gradesRegular.length; i++){
            gradesRegular[i] = convertToGPA(gradesRegular[i], "Regular");
        }

        double sum = 0.0;

        for(int i =0; i<gradesAP.length; i++){
            sum+= gradesAP[i];
        }
        for(int i =0; i<gradesHonors.length; i++){
            sum+=gradesHonors[i];
        }
        for(int i = 0; i<gradesRegular.length; i++){
            sum+=gradesRegular[i];
        }

        int numCourses = gradesAP.length + gradesHonors.length + gradesRegular.length;

        return sum/numCourses;
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

    public static double rankingGPA(Scanner scanner){
        System.out.println("What grade are you in? Please only enter numbers (ex. 10)");
        int grade = scanner.nextInt();

        System.out.println("What semester are you in right now?");
        int semester = scanner.nextInt();

        if(grade==10&&semester==1){
            highestGrades(scanner, "math", 1);
            highestGrades(scanner, "english", 1);
            highestGrades(scanner, "science", 1);
            highestGrades(scanner, "history", 1);
        }else if(grade==10&&semester==2){
            highestGrades(scanner, "math", 2);
            highestGrades(scanner, "english", 2);
            highestGrades(scanner, "science", 2);
            highestGrades(scanner, "history", 1);
        }else if(grade==11&&semester==1){
            highestGrades(scanner, "math", 2);
            highestGrades(scanner, "english", 2);
            highestGrades(scanner, "science", 2);
            highestGrades(scanner, "history", 2);
        }else if(grade==11&&semester==2){
            highestGrades(scanner, "math", 3);
            highestGrades(scanner, "english", 3);
            highestGrades(scanner, "science", 3);
            highestGrades(scanner, "history", 2);
        }else if(grade==12&&semester==1){
            highestGrades(scanner, "math", 3);
            highestGrades(scanner, "english", 3);
            highestGrades(scanner, "science", 3);
            highestGrades(scanner, "history", 2);
        }else if(grade==12&&semester==2){
            highestGrades(scanner, "math", 4);
            highestGrades(scanner, "english", 4);
            highestGrades(scanner, "science", 4);
            highestGrades(scanner, "history", 3);
        }else{
            System.out.println("Please input your grade again. Note that ranking only starts in 10th grades.");
        }
    }

    public static double[] highestGrades(Scanner scanner, String subject, int number){
        double[] grades = new double[number];
        for(int i = 0; i<number; i++){   
            System.out.println("What is the grade for " + subject + "class " + (i+1));
            grades[i]=scanner.nextDouble();
        }
        return grades;
    }

    public static double convertToGPA(double grade, String courseType){
        
        double[] thresholds = new double[]{97, 93, 90, 87, 83, 80, 77, 73, 71, 70, 60};
        double[] gpas;

        switch(courseType){
            case "AP":
                gpas = new double[]{5.0, 4.8, 4.6, 4.4, 4.2, 4.0, 3.8, 3.6, 3.4, 3.0, 2.0};
                break;
            case "Honors":
                gpas = new double[]{4.5, 4.3, 4.1, 3.9, 3.7, 3.5, 3.3, 3.1, 2.9, 2.5, 1.5};
                break;
            case "Regular":
                gpas = new double[]{4.0, 3.8, 3.6, 3.4, 3.2, 3.0, 2.8, 2.6, 2.4, 2.0, 1.0};
                break;
            default:
                return 0.0;
        
        }

        for(int i = 0; i< thresholds.length; i++){
            if (grade >= thresholds[i]){
                return gpas[i];
            }
        }

        return 0.0;
    }
    
}