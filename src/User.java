import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String fname;
    private String lname;
    private int age;
    private String number;
    private int status; // 0 - blocked, 1 - default, 2 - admin
    private String login;
    private String password;

    public User(int id, String fname, String lname, int age, String number, int status, String login, String password) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.number = number;
        this.status = status;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", age=" + age +
                ", number='" + number + '\'' +
                ", status=" + status +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String toString1() {
        return "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
