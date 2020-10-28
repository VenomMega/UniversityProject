import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class Student extends User {
    private String group;
    private TreeMap<String, ArrayList<Integer>> marks;

    public Student(int id, String fname, String lname, int age, String number, int status, String login, String password, String group, TreeMap<String, ArrayList<Integer>> marks) {
        super(id, fname, lname, age, number, status, login, password);
        this.group = group;
        this.marks = marks;
    }

    public Student(int id, String fname, String lname, int age, String number, int status, String login, String password, String group) {
        super(id, fname, lname, age, number, status, login, password);
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public TreeMap<String, ArrayList<Integer>> getMarks() {
        return marks;
    }

    public void setMarks(TreeMap<String, ArrayList<Integer>> marks) {
        this.marks = marks;
    }

    public Student(int id, String fname, String lname, int age, String number, int status, String login, String password) {
        super(id, fname, lname, age, number, status, login, password);
    }

    @Override
    public void changePassword(String newPassword) {
        super.changePassword(newPassword);
    }

    @Override
    public String toString() {
        return "Student{" + "ID: " + super.getId() +
                " Name: '" + super.getFname() + '\'' +
                '}';
    }
}
