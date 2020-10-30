
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.Serializable;
import java.util.TreeMap;

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
            else if (instr == 2){
                teacherLog();
            }
            else if (instr == 3){
                studentReg();
            }
            else if (instr == 4){
                teacherReg();
            }
        }
    }


    public void teacherLog() throws IOException, ClassNotFoundException {
//        ArrayList<Teacher> deserList2 = new ArrayList<>();
        FileInputStream fileIn = new FileInputStream("teachers.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileIn);
        teachers = (ArrayList<Teacher>) objectInputStream.readObject();
        for (Teacher teacher: teachers){
            System.out.println(teacher);
        }
        System.out.println("Input teacher login");
        Scanner sc = new Scanner(System.in);
        String ourLogin = sc.next();
        for (int i=0;i<teachers.size();i++){
            int index;
            if (teachers.get(i).getLogin().equals(ourLogin)){
                index = i;
                System.out.println("Hello " + teachers.get(i).getLogin());
                System.out.println("Enter your password: ");
                String ourPassword = sc.next();
                if (teachers.get(i).getPassword().equals(ourPassword)){
                    System.out.println("Welcome sir teacher sir");
                    boolean action2 = true;
                    while (action2){
                        System.out.println("[1] Show information\n" +
                                "[2] Change password\n" +
                                "[3] Change marks\n" +
                                "[0] Exit");
                        int instr = sc.nextInt();
                        if (instr == 1){
                            System.out.println(teachers.get(index).toString());
                            System.out.println(teachers.get(index).getPassword());
                        }
                        else if (instr == 2){
                            System.out.println("Enter your current password");
                            String currentPassword = sc.next();
                            if (currentPassword.equals(ourPassword)){
                                System.out.println("Enter a new password: ");
                                String newPassword = sc.next();
                                System.out.println("Enter your password again");
                                String newPassword1 = sc.next();
                                if (newPassword1.equals(newPassword)){
                                    teachers.get(index).setPassword(newPassword);
                                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teachers.txt", false));
                                    oos.writeObject(teachers);
                                }
                            }
                        }
                        else if (instr == 3){
                            ArrayList<Integer> stud = new ArrayList<>();
                            for (int j = 0; j < teachers.size();j++){
                                for (int k = 0; k < teachers.get(j).getGrades().size(); k++){
                                    for (int l = 0; l < students.size(); l++){
                                        if (teachers.get(j).getGrades().get(k).equals(students.get(l).getGroup())){
                                            stud.add(l);
                                            System.out.println(students.get(l).toString());

                                        }

                                    }
                                }
                            }
                            System.out.println("Input a student ID which marks you want to change");
                            int studId = sc.nextInt();
                            int mark = 9;
                            ArrayList<Integer> marks = new ArrayList<>();
                            for (int o = 0; o < students.size(); o++){
                                if (students.get(stud.get(o)).getId() == studId){
                                    System.out.println("Input a marks \"0\" to stop ");
                                    while (mark != 0){
                                        mark = sc.nextInt();
                                        marks.add(mark);
                                    }
                                    marks.remove(marks.size()-1);
                                    TreeMap<String, ArrayList<Integer>> marks2 = new TreeMap<>();
                                    marks2.put(teachers.get(index).getSubject(), marks);
                                    students.get(studId).setMarks(marks2);
                                    System.out.println(students.get(studId).getMarks());
                                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt", false));
                                    oos.writeObject(students);
                                }
                            }
                        }
                        else if (instr == 0){
                            start();
                        }
                    }
                }
            }
        }
    }

    public void studentLog() throws IOException, ClassNotFoundException {
//        ArrayList<Student> deserList = new ArrayList<>();

        FileInputStream fileIn = new FileInputStream("students.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileIn);
        students = (ArrayList<Student>) objectInputStream.readObject();
        for (Student student: students){
            System.out.println(student);
        }
//        for (Student s: students){
//            System.out.println(s);
//        }
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
                    String currentPassword = sc.next();
                    if (currentPassword.equals(ourPassword)){
                        System.out.println("Enter a new password: ");
                        String newPassword = sc.next();
                        System.out.println("Enter your password again");
                        String newPassword1 = sc.next();
                        if (newPassword1.equals(newPassword)){
                            students.get(ourId).setPassword(newPassword);
                            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt", false));
                            oos.writeObject(students);
                        }
                    }
                }
                else if (instr == 3){
                    System.out.println(students.get(ourId).getMarks());

                }
                else if (instr == 0){
                    start();
                }
            }
        } else {
            System.out.println("Poshel nahyi dolbaeb");
            start();
        }
    }

    public void studentReg() throws IOException, ClassNotFoundException {
//        FileReader fr = new FileReader("students.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt", false));
//        FileWriter fw = new FileWriter("students.txt");

//        BufferedReader br = new BufferedReader(fr);
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
//        FileWriter fw = new FileWriter( "students.txt" );
       // FileOutputStream fos = new FileOutputStream("students.txt", true);
       // ObjectOutputStream oos = new ObjectOutputStream(fos);
        Student st = new Student(count,fname,lname,age,number,status,login,password,group);
        students.add(st);
        oos.writeObject(students);
        System.out.println("Student has been registered.");

        //int id String fname, String lname, int age, String number, int status, String login, String password, String group
    }

    public void teacherReg() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teachers.txt", false));
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
        System.out.println("Input a subject");
        String subject = sc.next();
        System.out.println("Input a groups (\"stop\" to finish)");
        String group = "kek";
        ArrayList<String> groups = new ArrayList<>();
        while (!group.equals("stop")){
            group = sc.next();
            groups.add(group);
        }
        groups.remove(groups.size()-1);
        System.out.println(groups);
        Teacher t1 = new Teacher(count,fname,lname,age,number,status,login,password,subject,groups);
        teachers.add(t1);
        oos.writeObject(teachers);
        System.out.println("Teacher has been registered.");
    //    int id, String fname, String lname, int age, String number, int status, String login, String password, String subject, ArrayList<String> grades
    }
}
