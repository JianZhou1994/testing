import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private String address = "Address Unknown";
    private Integer age;
    private Map<Course, Double> courses = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Map<Course, Double> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return name + ", " + address + ", " + age;
    }
}