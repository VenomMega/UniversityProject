
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.Serializable;

public class Interface implements Serializable {
    int count = -1;
    List<Student> students = new ArrayList<>();
    List<Teacher> teachers = new ArrayList<>();
    public void start() throws IOException, ClassNotFoundException {
        boolean action = true;

        while (action){
            Scanner sc = new Scanner(System.in);
            System.out.println("введите 1 чтобы залогиниться как ученик\n" +
                    "введите 2 чтобы залогиниться как учитель\n" +
                    "введите 3 чтобы зарегаться как ученик\n" +
                    "введите 4 чтобы зарегаться как учитель"); // replace this shit
            int instr = sc.nextInt();
            if (instr == 1){
                studentLog();
            }
            else if (instr == 3){
                studentReg();
            }
        }
    }


    public void studentLog() throws IOException, ClassNotFoundException {
        for (Student s: students){
            System.out.println(s);
        }
        System.out.println("Input student ID");
        Scanner sc = new Scanner(System.in);
        int ourId = sc.nextInt();
        System.out.println("Hello " + students.get(ourId).getLogin());
        System.out.println("Enter your password:");
        String ourPassword = sc.next();
        if (students.get(ourId).getPassword().equals(ourPassword)){
            System.out.println("Welcome dear student");
            boolean action = true;
            while (action){
                System.out.println("[1] Show information\n" +
                        "[2] Change password\n" +
                        "[3] Show marks\n" +
                        "[0] Exit");
                int instr = sc.nextInt();
                if (instr == 1){
                    System.out.println(students.get(ourId).toString());
                    System.out.println(students.get(ourId).getPassword());
                }
                else if (instr == 2){
                    System.out.println("Enter your current password");
                    String currenPassword = sc.next();
                    if (currenPassword.equals(ourPassword)){
                        System.out.println("Enter a new password: ");
                        String newPassword = sc.next();
                        System.out.println("Enter your password again");
                        String newPassword1 = sc.next();
                        if (newPassword1.equals(newPassword)){
                            students.get(ourId).setPassword(newPassword);
                        }
                    }
                }
                else if (instr == 3){
                    System.out.println(students.get(ourId).getMarks());

                }
                else if (instr == 4){

                }
            }
        } else {
            System.out.println("Poshel nahyi dolbaeb");
            start();
        }
    }

    public void studentReg() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        count++;
        System.out.println("Input a first name");
        String fname = sc.next();
        System.out.println("Input a last name");
        String lname = sc.next();
        System.out.println("Input a age");
        int age = sc.nextInt();
        System.out.println("Input a number");
        String number = sc.next();
        int status = 1;
        System.out.println("Input a login");
        String login = sc.next();
        System.out.println("Input a password");
        String password = sc.next();
        System.out.println("Input a group");
        String group = sc.next();

        FileOutputStream fos = new FileOutputStream("students.txt", true);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
 //       ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt", true));
        FileWriter fw = new FileWriter( "students.txt" );

        Student st = new Student(count,fname,lname,age,number,status,login,password,group);
        students.add(st);
        oos.writeObject(st);
        oos.write('\n');
        oos.writeChars("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        oos.write('\n');
        oos.close();

//        fw.write('\n');
//        fw.write("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
//        fw.write('\n');
//            oos.writeObject('\n');
//            oos.writeObject("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
//            oos.writeObject('\n');
        System.out.println("Student has been registered.");

        //int id String fname, String lname, int age, String number, int status, String login, String password, String group
    }
}
