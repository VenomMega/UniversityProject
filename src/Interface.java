
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class Interface implements Serializable {
    int count = -1;
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Teacher> teachers = new ArrayList<>();
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
                    String newPassword = sc.next();
                    if (newPassword.equals(ourPassword)){
                        System.out.println("Enter a new password: ");
                        String newPassword1 = sc.next();
                        if (newPassword1.equals(newPassword)){

                            System.out.println(students.get(ourId).getPassword());
                            File file = new File("students1.txt");
                            File file2 = new File("students.txt");

                            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, false));
                            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file2));
                            ArrayList<Student> studentsRestored = (ArrayList<Student>) ois.readObject();
                            for (int i=0;i<students.size();i++){
                                oos.writeObject(studentsRestored);
                                oos.writeObject(" ");
                            }

                            file.renameTo(file2);
                            file.delete();

                        }
                    }
                }
                else if (instr == 3){
                    System.out.println(students.get(ourId).getMarks());

                }
            }
        } else {
            System.out.println("Poshel nahyi dolbaeb");
            start();
        }
    }

    public void studentReg(){
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

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt", true)))
        {
            Student st = new Student(count,fname,lname,age,number,status,login,password,group);
            students.add(st);
            oos.writeObject(st);
            oos.writeObject(" ");
            System.out.println("Student has been registered.");
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }

        //int id String fname, String lname, int age, String number, int status, String login, String password, String group
    }
}
