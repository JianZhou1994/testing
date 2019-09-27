import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ChooseCourse {
    private Course ItalianCooking;
    private Course seafood;
    private Course sewing;
    private Course creativeWriting;
    private Course businessWriting;
    private List<Student> students; //contains all the students
    Scanner in = new Scanner(System.in);

    public ChooseCourse() {
        ItalianCooking = new Course(1, 10, 500.0, 1000.0);
        seafood = new Course(2, 10, 700.0, 1000.0);
        sewing = new Course(3, 10, 300.0, 0.0);
        creativeWriting = new Course(4, 15, 200.0, 800.0);
        businessWriting = new Course(5, 15, 200.0, 600.0);
        students = new ArrayList<>();
    }

    public void addStudent() {
        Student student = new Student();
        Boolean isExist = false;
        //select student information
        System.out.print("Please enter your name:");
        String name = in.next();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals(name)) {
                student = students.get(i);
                isExist = true;
            }
        }

        if (!isExist) {
            student.setName(name);
            System.out.print("Please enter your age: ");
            Integer age = in.nextInt();
            student.setAge(age);
            System.out.print("Please enter your address if you like. You also can enter pass to skip this: ");
            String address = in.next();
            if (!"pass".equals(address)) {
                student.setAddress(address);
            }
        }

        //judge which course the student will be add in
        Course course = showCourses();
        if (course == null) {
            return;
        }
        if (course == this.sewing) {
            course.setCost(course.getCost() + 100);
        }
        //judge the student is the first time choose course or not
        Double charge;
        if (students.contains(student)) {
            charge = course.addStudent(false);
        } else {
            charge = course.addStudent(true);
        }
        //judge the course is full or not
        if (course.isFull()) {
            System.out.println("The course you choose has been full, please check it and try again.");
            return;
        }
        //judge the student is already in the course or not
        Iterator iterator = course.getStudents().iterator();
        while (iterator.hasNext()) {
            Student s = (Student) iterator.next();
            if (s.getName().equals(name)) {
                System.out.println("Student called " + name + " already exists, please check it and try again.");
                return;
            }
        }
        student.getCourses().put(course, charge);
        students.add(student);
        course.getStudents().add(student);
        System.out.println("Add student successfully.");
    }

    public void withdrawStudent() {
        if (students.size() == 0) {
            System.out.println("There is no student, please add one first.");
            return;
        }
        System.out.print("Please enter name: ");
        String name = in.next();
        Boolean isExist = false;
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getName().equals(name)) {
                isExist = true;
                Course course = showCourses();
                if (course == null) {
                    return;
                }
                if (course == this.sewing) {
                    course.setCost(course.getCost() - 100);
                }
                course.withdrawStudent(student);
                if (student.getCourses().size() == 0) {
                    students.remove(student);
                }
                System.out.println("Withdraw the student named " + student.getName() + " successfully.");
                break;
            }
        }
        if (!isExist) {
            System.out.println("The student named " + name + " does not exist.");
        }
    }

    private void displayStudent() {
        Course course = showCourses();
        course.printStudents();
    }

    private void displayCourse() {
        System.out.println("Italian Cooking: " + ItalianCooking);
        System.out.println("Seafood Cooking: " + seafood);
        System.out.println("Sewing: " + sewing);
        System.out.println("Creative Writing: " + creativeWriting);
        System.out.println("Business Writing: " + businessWriting);
    }

    /**
     * judge which option user choose
     * @param option
     */
    public void option(Integer option) {
        if (option == 1) {
            this.addStudent();
        } else if (option == 2) {
            this.withdrawStudent();
        } else if (option == 3) {
            this.displayStudent();
        } else {
            this.displayCourse();
        }
    }

    public Course showCourses() {
        System.out.println("001 Italian Cooking");
        System.out.println("002 Seafood");
        System.out.println("003 Sewing");
        System.out.println("004 Creative Writing");
        System.out.println("005 Business Writing");
        System.out.print("Please choose course first: ");

        Integer courseNumber = in.nextInt();
        Course course;
        if (courseNumber == ItalianCooking.getId()) {
            course = this.ItalianCooking;
        } else if (courseNumber == seafood.getId()) {
            course = this.seafood;
        } else if (courseNumber == sewing.getId()) {
            course = this.sewing;
        } else if (courseNumber == creativeWriting.getId()) {
            course = this.creativeWriting;
        } else if (courseNumber == businessWriting.getId()) {
            course = this.businessWriting;
        } else {
            System.out.println("The course you choose does not exist, please check it and try again.");
            return null;
        }
        return course;
    }
}
