import java.io.Serializable;
import java.util.ArrayList;

public class Teacher extends User {
    private String subject;
    private ArrayList<String> grades; // groups

    public Teacher(int id, String fname, String lname, int age, String number, int status, String login, String password, String subject, ArrayList<String> grades) {
        super(id, fname, lname, age, number, status, login, password);
        this.subject = subject;
        this.grades = grades;
    }

    public Teacher(int id, String fname, String lname, int age, String number, int status, String login, String password) {
        super(id, fname, lname, age, number, status, login, password);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ArrayList<String> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<String> grades) {
        this.grades = grades;
    }
}
