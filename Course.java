import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Course {
    private Integer id;
    private String teacherName;
    private Integer number;//number of students enerolled
    private Integer maximumNumber;//maximum number of student allowed
    private Double charge;//standard charge per student
    private Double income;
    private Double cost;
    private Double profit;
    private List<Student> students = new ArrayList<>();

    public Course() {
    }

    public Course(Integer id, Integer maximumNumber, Double charge, Double cost) {
        this.id = id;
        this.teacherName = "Unknown";
        this.number = 0;
        this.maximumNumber = maximumNumber;
        this.charge = charge;
        this.cost = cost;
        this.income = 0.0;
        this.profit = income - cost;
    }

    /**
     * to print the students who have chosen the course
     */
    public void printStudents() {
        Iterator iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = (Student) iterator.next();
            System.out.println(s);
        }
    }

    @Override
    public String toString() {
        String str = "Students " + students.size() +
                ", Income " + income +
                ", Cost " + cost +
                ", Profit " + profit;
        return str;
    }

    /**
     * @return if this course is full, the function will return true
     */
    public boolean isFull() {
        if (number == maximumNumber) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * modify course's  profit andstudent number
     * @param b if the student is the first time choose course here, true
     * @return this student real pay for the course
     */
    public Double addStudent(boolean b) {
        Double money;
        if (b) {
            money = charge;
        } else {
            money = charge * 0.8;
        }
        income += money;
        this.profit = income - cost;
        number++;
        return money;
    }

    /**
     *
     * @param student withdraw which student from this course
     */
    public void withdrawStudent(Student student) {
        income = income - student.getCourses().get(this);
        this.profit = income - cost;
        student.getCourses().remove(this);
        students.remove(student);
    }

    public Integer getId() {
        return id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
        this.profit = income - cost;
    }

    public List<Student> getStudents() {
        return students;
    }
}
