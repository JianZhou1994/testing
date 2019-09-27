import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        ChooseCourse chooseCourse = new ChooseCourse();
        while (true){
            displayMenu();
            Scanner in = new Scanner(System.in);
            try{
                Integer option = in.nextInt();
                if (option < 1 || option > 5){
                    System.out.println("Please enter a number from 1 to 5.");
                    continue;
                }
                if (option == 5){
                    break;
                }
                chooseCourse.option(option);
            } catch (InputMismatchException e){
                //If user enter a string, an error called InputMismatchException will be reported.
                System.out.println("Please input a number!");
                continue;
            }
        }
    }

    /**
     * to display menu in console
     */
    private static void displayMenu(){
        System.out.println("Course Management");
        System.out.println("===============");
        System.out.println("1. Add a student");
        System.out.println("2. Withdraw a student");
        System.out.println("3. Display a student list for a course");
        System.out.println("4. Display the course figures");
        System.out.println("5. Quit");
        System.out.print("Enter an option:");
    }
}
