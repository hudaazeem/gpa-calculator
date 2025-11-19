/*TODO
- [DONE] color coded output - ansi colors
- [DONE] rounded results to 6-7 decimals
- [DONE] input validation
- [DONE]are you sure confirmation? when exiting
- predictive gpa tool
- [done] custom weight profiles

- autosave last run??
- gui version
- export gpa report




*/






import java.util.Scanner;
import java.util.Arrays;



public class CalculatorGPA{
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean running = true; 

        System.out.println("Welcome to my project!");
        System.out.println("This program will calculate all of your GPAs based on the Dallas ISD system.");
            
        while(running){
            
            System.out.println("Select any of the options below!");
            System.out.println("[1] Calculate class grade (default weightages)");
            System.out.println("[2] Calculate class grade (custom weightings)")
            System.out.println("[3] Calculate cumulative GPA");
            System.out.println("[4] Calculate unweighted GPA");
            System.out.println("[5] Calculate weighted GPA");
            System.out.println("[6] Calculate ranking GPA");
            System.out.println("[7] Estimate future grades");
            System.out.println("[8] Exit");
            System.out.println("[0] Info");
            double average;
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    average = calculateClassGrade(scanner);
                    System.out.println("Your grade for the class is " + formatCumulGrade(average) + "!");
                    waitForEnter(scanner);
                    break;
                case 2:
                    average = calculateCustomWeightedGrade(scanner);
                    System.out.println("Your grade for this class is " + formatCumulGrade(average)+"!");
                    waitForEnter(scanner);
                    break;
                case 3: 
                    average = calculateCumulativeGPA(scanner);
                    System.out.println("Your cumulative GPA is " + formatGPA(average) + "!");
                    waitForEnter(scanner);
                    break;
                case 4:
                    average = calculateCumulativeGPA(scanner);
                    double averageUnweighted = average/25;
                    System.out.println("Your unweighted GPA is " + formatGPA(averageUnweighted) + "!");
                    waitForEnter(scanner);
                    break;
                case 5:
                    average = calculateWeightedGPA(scanner);
                    System.out.println("Your weighted GPA is " + formatGPA(average) + "!");
                    waitForEnter(scanner);
                    break;
                case 6:
                    average = rankingGPA(scanner);
                    System.out.println("Your ranking GPA is "+ formatGPA(average) + "!");
                    waitForEnter(scanner);
                    break;
                case 7:
                    System.out.println("Do you want to calculate weighted GPA? (y/n)");
                    String weightedChoice = scanner.next();
                    boolean weighted = weightedChoice.equalsIgnoreCase("y");
                    average = predictiveGPA(scanner, weighted);
                    System.out.println("Your projected GPA is " + formatGPA(average)+ "!");
                    waitForEnter(scanner);
                    break;
                case 8:
                    System.out.println("Are you sure? (y/n)");
                    String confirm = scanner.next();
                    if(confirm.equalsIgnoreCase("y")){
                        running = false;
                        System.out.println("Bye!!");
                    }else{
                        System.out.println("Returning to main menu...");
                    }
                    
                    break;
                case 0:
                    System.out.println("===== GPA Calculator Info =====");
                    System.out.println("This program calculates your GPA based on Dallas ISD's regulations");
                    System.out.println("You can choose from the following options:");
                    System.out.println("1 - Calculate class grade (weighted by projects, daily grades, tests, etc.) *Note that it uses the general percentage, although your teacher may have a different system set in place*");
                    System.out.println("2 - Calculate cumulative gpa (gives you your overall average)");
                    System.out.println("3 - Calculate unweighted GPA (All courses are weighed equally)");
                    System.out.println("4 - Calculate weighted GPA (AP/IB and honors/dual credit classes have higher weightings)");
                    System.out.println("5 - Calculate ranking GPA (this is used to determine your class rank, if your school does this)");
                    System.out.println("6 - Exit the program");

                    System.out.println("\nInstructions:");
                    System.out.println("--> Enter numeric choices to navigate the menu");
                    System.out.println("--> Enter grades as numbers out of 100");
                    System.out.println("--> Press enter after each input when prompted");
                    System.out.println("--> After each calculation, press enter to return to the main menu");
                    waitForEnter(scanner);
                    break;
                default:
                    System.out.println("Invalid choice, please try again!");
            }
        }    
    }

    public static String formatGPA(double gpa){
        gpa = Math.round(gpa*1000000.0)/1000000.0;
        if(gpa>=3.5){
            return GREEN + gpa + RESET;
        }else if(gpa >=2.5){
            return YELLOW + gpa + RESET; 
        }else{
            return RED + gpa + RESET;
        }
    }
        
    public static String formatCumulGrade(double grade){
        grade = Math.round(grade*1000000.0)/1000000.0;
        if(grade>=85){
            return GREEN + grade + RESET;
        }else if(grade>=70){
            return YELLOW + grade + RESET;
        }else{
            return RED + grade + RESET;
        }
    }
    public static void waitForEnter(Scanner scanner){
        System.out.println("Press enter to return to the main menu");
        scanner.nextLine();
        scanner.nextLine();
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
        if(grades.length==0){
            System.out.println("Error: You must enter atleast one class.");
            return 0.0;
        }
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

        if(numCourses==0){
            System.out.println("Error: No courses entered.");
            return 0.0;
        }

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

        System.out.println("Enter the number of " + category + " grades you have.");
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

        if(numCourses ==0){
            System.out.println("Error: You must have at least one assignment.");
            return 0.0;
        }

        double average = sum/numCourses;

        return average;
    }

    public static double rankingGPA(Scanner scanner){
        System.out.println("What grade are you in? Please only enter numbers (ex. 10)");
        int grade = scanner.nextInt();

        System.out.println("What semester are you in right now?");
        int semester = scanner.nextInt();
        double total=0;
        if(grade==10&&semester==1){
            double mathGrades = averageHighestGrades(scanner, "math", 1);
            double engGrades = averageHighestGrades(scanner, "english", 1);
            double sciGrades = averageHighestGrades(scanner, "science", 1);
            double histGrades = averageHighestGrades(scanner, "history", 1);
            total = mathGrades+engGrades+sciGrades+histGrades;
            double rankingGPA = total/4;
            return rankingGPA;
        }else if(grade==10&&semester==2){
            double mathGrades = averageHighestGrades(scanner, "math", 2);
            double engGrades = averageHighestGrades(scanner, "english", 2);
            double sciGrades = averageHighestGrades(scanner, "science", 2);
            double histGrades = averageHighestGrades(scanner, "history", 1);
            total = 2*mathGrades+2*engGrades+2*sciGrades+histGrades;
            double rankingGPA = total/7;
            return rankingGPA;
        }else if(grade==11&&semester==1){
            double mathGrades = averageHighestGrades(scanner, "math", 2);
            double engGrades = averageHighestGrades(scanner, "english", 2);
            double sciGrades = averageHighestGrades(scanner, "science", 2);
            double histGrades = averageHighestGrades(scanner, "history", 2);
            total = 2*mathGrades+2*engGrades+2*sciGrades+2*histGrades;
            double rankingGPA = total/8;
            return rankingGPA;
        }else if(grade==11&&semester==2){
            double mathGrades = averageHighestGrades(scanner, "math", 3);
            double engGrades = averageHighestGrades(scanner, "english", 3);
            double sciGrades = averageHighestGrades(scanner, "science", 3);
            double histGrades = averageHighestGrades(scanner, "history", 2);
            total = 3*mathGrades+3*engGrades+3*sciGrades+2*histGrades;
            double rankingGPA = total/11;
            return rankingGPA;
        }else if(grade==12&&semester==1){
            double mathGrades = averageHighestGrades(scanner, "math", 3);
            double engGrades = averageHighestGrades(scanner, "english", 3);
            double sciGrades = averageHighestGrades(scanner, "science", 3);
            double histGrades = averageHighestGrades(scanner, "history", 2);
            total = 3*mathGrades+3*engGrades+3*sciGrades+2*histGrades;
            double rankingGPA = total/11;
            return rankingGPA;
        }else if(grade==12&&semester==2){
            double mathGrades = averageHighestGrades(scanner, "math", 4);
            double engGrades = averageHighestGrades(scanner, "english", 4);
            double sciGrades = averageHighestGrades(scanner, "science", 4);
            double histGrades = averageHighestGrades(scanner, "history", 3);
            total = 4*mathGrades+4*engGrades+4*sciGrades+3*histGrades;
            double rankingGPA = total/15;
            return rankingGPA;
        }else{
            System.out.println("Please input your grade again. Note that ranking only starts in 10th grades.");
            return 0.0;
        }


    }

    public static double averageHighestGrades(Scanner scanner, String subject, int number){
        System.out.println("How many " + subject + "classes do you have?");
        int numCourses = scanner.nextInt();

        double[] grades = new double[numCourses];

        for(int i = 0; i<number; i++){   
            System.out.println("What is the grade for " + subject + "class " + (i+1) +":");
            grades[i]=scanner.nextDouble();
            

        }
        Arrays.sort(grades);
        double sumTop=0;
        for(int i = numCourses -1; i>=numCourses-number; i--){
            sumTop += grades[i];
        }

        if(number == 0){
            System.out.println("Error: Can't average zero grades");
            return 0.0;
        }

        if(numCourses < number){
            System.out.println("Error: You only entered "+ numCourses+" grades, but you need "+number+".");
        }

        return sumTop/number;
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
    
    public static double calculateCustomClassGrade(Scanner scanner){
        int numCategories = scanner.nextInt();
        double[] percentages = new double[numCategories];
        for(int i =0; i<numCategories; i++){
            System.out.println("How many assignments do you have for category "+ i+"?");
            
        }
        return 6.7;
    }

    public static double calculateCustomWeightedGrade(Scanner scanner){
        System.out.println("How many different categories are present?");
        int numCategories = scanner.nextInt();
        double[] weightings = new double[numCategories];
        double[] categoryAverages = new double[numCategories];


        for(int i =0; i<numCategories; i++){
            System.out.println("What is your weighting for category "+ i+"? Please write it as a decimal (eg 60% would be 0.6)");
            weightings[i] = scanner.nextDouble();
        }
        double sum=0;
        for(int i = 0; i<numCategories; i++){
            sum+=weightings[i];
        }

        if(Math.abs(sum-1.0)>0.0001){
            System.out.println("Oops! Your percentages don't add up... please try again.");
            return 0.0;
        }

        for(int i =0; i<numCategories; i++){
            double[] grades = receiveGrades(scanner, "category " + (i+1));

            double total = 0;

            for(double g: grades) total +=g;

            if(grades.length==0){
                System.out.println("Error: Category " + (i+1)+" has no grades.");
            }

            categoryAverages[i] = total/grades.length;
        }

        double finalGrade = 0;
        for(int i = 0; i< numCategories; i++){
            finalGrade += categoryAverages[i]*weightings[i];
        }

        return finalGrade;
    }

    public static double predictiveGPA(Scanner scanner, boolean weighted){
        double[] currentGrades = receiveGrades(scanner, "current");

        double[] futureGrades = receiveGrades(scanner, "future");

        if(currentGrades.length + futureGrades.length == 0){
            System.out.println("Error: no grades entered");
            return 0.0;
        }

        if(weighted){
            currentGrades = convertGradesToWeighted(scanner, currentGrades, "current");
            futureGrades = convertGradesToWeighted(scanner, futureGrades, "future");
        }

        double sum = 0.0;

        int totalCourses = currentGrades.length + futureGrades.length;

        for(double g: currentGrades) sum +=g;
        for(double g: futureGrades) sum += g;

        return sum/totalCourses;
    }

    public static double[] convertGradesToWeighted(Scanner scanner, double[] grades, String label);
    for(int i = 0; i<grades.length; i++){
        System.out.println("For " + label + " class " + (i+1)+ ", what type is this course? (AP/Honors/Regular)");
        String type = scanner.next();
        grades[i] = convertToGPA(grades[i], type);
    }
}