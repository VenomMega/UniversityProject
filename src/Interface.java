import java.io.*;
import java.util.*;
import java.io.Serializable;

public class Interface implements Serializable {
    int count = -1;
    List<Student> students = new ArrayList<>();
    List<Teacher> teachers = new ArrayList<>();
    public void start() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileIn = new FileInputStream("teachers.txt");
            FileInputStream fileIn2 = new FileInputStream("students.txt");
            ObjectInputStream objectInputStream2 = new ObjectInputStream(fileIn2);
            students = (ArrayList<Student>) objectInputStream2.readObject();
            if (students.size() == 0){
                count = 0;
            }
            else if (teachers.size() == 0){
                count = 0;
            } else if (students.get(students.size()-1).getId() > teachers.get(teachers.size()-1).getId()){
                count = students.get(students.size()-1).getId();
            } else count = teachers.get(teachers.size()-1).getId();

        } catch (EOFException e) {
            System.out.println("File empty");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        boolean action = true;

        while (action){
            Scanner sc = new Scanner(System.in);
            System.out.println("[1] Log in as student\n" +
                    "[2] Log in as teacher\n" +
                    "[3] Register new student\n" +
                    "[4] Register new teacher\n" +
                    "[0] Exit");
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
            else if (instr == 0){
                action = false;
            }
            else {
                System.out.println("Invalid input");
            }
        }
    }


    public void teacherLog() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("teachers.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileIn);
        teachers = (ArrayList<Teacher>) objectInputStream.readObject();
        for (Teacher teacher: teachers){
            System.out.println(teacher.toString1());
        }
        System.out.println("Input teacher login");
        Scanner sc = new Scanner(System.in);
        try {
            String ourLogin = sc.next();
            if (ourLogin.equals("admin")){
                adminPanel();
            }
            for (int i=0;i<=teachers.size();i++){
                int index;
                if (teachers.get(i).getLogin().equals(ourLogin)){
                    index = i;
                    if (teachers.get(index).getStatus() == 2){
                        adminPanel();
                    }
                    System.out.println("Hello " + teachers.get(index).getLogin());
                    System.out.println("Enter your password: ");
                    String ourPassword = sc.next();
                    if (teachers.get(index).getPassword().equals(ourPassword)){
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
                                                System.out.println(students.get(l).toString1());

                                            }

                                        }
                                    }
                                }
                                System.out.println("Input a student ID which marks you want to change");
                                int studId = sc.nextInt();
                                int mark = 9;
                                ArrayList<Integer> marks = new ArrayList<>();
                                for (int o = 0; o <= students.size()-1; o++){
                                    int ourIndex1 = stud.get(o);
                                    if (students.get(ourIndex1).getId() == studId){
                                        System.out.println("Input a marks \"0\" to stop ");
                                        while (mark != 0){
                                            mark = sc.nextInt();
                                            marks.add(mark);
                                        }
                                        marks.remove(marks.size()-1);
                                        TreeMap<String, ArrayList<Integer>> marks2 = new TreeMap<>();
                                        marks2.put(teachers.get(index).getSubject(), marks);
                                        students.get(studId).setMarks(marks2);
                                        System.out.println("Marks successful updated");
                                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt", false));
                                        oos.writeObject(students);
                                    }
                                    else continue;
                                }
                            }
                            else if (instr == 0){
                                start();
                                action2 = false;
                            } else System.out.println("Invalind input");
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input");
        }

    }

    public void studentLog() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("students.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileIn);
        students = (ArrayList<Student>) objectInputStream.readObject();
        for (Student student: students){
            if (student.getStatus() != 0){
                System.out.println(student.toString1());
            } else continue;
        }
        System.out.println("Input student login");
        Scanner sc = new Scanner(System.in);
        try {
            String ourLogin = sc.next();
            if (ourLogin.equals("admin")){
                adminPanel();
            }
            for (int i=0;i<students.size();i++) {
                int ourId;
                if (students.get(i).getLogin().equals(ourLogin)) {
                    ourId = i;
                    if (students.get(ourId).getStatus() == 2){
                        adminPanel();
                    }
                    System.out.println("Hello " + students.get(ourId).getLogin());
                    System.out.println("Enter your password:");
                    String ourPassword = sc.next();
                    if (students.get(ourId).getPassword().equals(ourPassword)) {
                        System.out.println("Welcome dear student");
                        boolean action = true;
                        while (action) {
                            System.out.println("[1] Show information\n" +
                                    "[2] Change password\n" +
                                    "[3] Show marks\n" +
                                    "[0] Exit");
                            int instr = sc.nextInt();
                            if (instr == 1) {
                                System.out.println(students.get(ourId).toString());
                                System.out.println(students.get(ourId).getPassword());
                            } else if (instr == 2) {
                                System.out.println("Enter your current password");
                                String currentPassword = sc.next();
                                if (currentPassword.equals(ourPassword)) {
                                    System.out.println("Enter a new password: ");
                                    String newPassword = sc.next();
                                    System.out.println("Enter your password again");
                                    String newPassword1 = sc.next();
                                    if (newPassword1.equals(newPassword)) {
                                        students.get(ourId).setPassword(newPassword);
                                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt", false));
                                        oos.writeObject(students);
                                    }
                                }
                            } else if (instr == 3) {
                                System.out.println(students.get(ourId).getMarks());

                            } else if (instr == 0) {
                                start();
                            }
                        }
                    } else {
                        System.out.println("Invalid password");
                        start();
                    }
                }
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input");
        }

    }

    public void studentReg() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt", false));
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
        for (int k = 0; k < teachers.size(); k++){
            while (login.equals(teachers.get(k).getLogin())){
                System.out.println("Input another login");
                login = sc.next();
            }
        }
        for (int l = 0; l < students.size(); l++){
            while (login.equals(students.get(l).getLogin())){
                System.out.println("Input another login");
                login = sc.next();
            }
        }
        System.out.println("Input a password");
        String password = sc.next();
        System.out.println("Input a group");
        String group = sc.next();
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
        for (int k = 0; k < teachers.size(); k++){
            while (login.equals(teachers.get(k).getLogin())){
                System.out.println("Input another login");
                login = sc.next();
            }
        }
        for (int l = 0; l < students.size(); l++){
            while (login.equals(students.get(l).getLogin())){
                System.out.println("Input another login");
                login = sc.next();
            }
        }
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

    public void adminPanel(){
        System.out.println("Input a password");
        Scanner sc = new Scanner(System.in);
        String adminPass = sc.next();
        if (adminPass.equals("admin")){
            boolean action3 = true;
            boolean action5 = true;
            System.out.println("Welcome my dear admin <3");
            System.out.println("Teachers: ");
            for (Teacher teacher : teachers) {
                System.out.println(teacher.toString());
            }
            System.out.println("Students: ");
            for (Student student : students) {
                System.out.println(student.toString());
            }
            while (action3) {
                System.out.println("You want to change teachers or students?");
                System.out.println("[1] Teachers\n" +
                        "[2] Students\n" +
                        "[3] Show banned users\n" +
                        "[0] Exit");
                int instr = sc.nextInt();
                if (instr == 1){
                    boolean action4 = true;
                    while (action4){
                        System.out.println("[1] Change login\n" +
                                "[2] Change password\n" +
                                "[3] Change status\n" +
                                "[4] Change number\n" +
                                "[5] Change subject\n" +
                                "[0] Exit");
                        int instr2 = sc.nextInt();
                        if (instr2 == 1){
                            for (Teacher teacher: teachers){
                                System.out.println(teacher.toString1());
                            }
                            System.out.println("Input a teacher's ID");
                            int teachId = sc.nextInt();
                            try {
                                System.out.println("Input a new login");
                                String newLogin = sc.next();
                                for (int k = 0; k < teachers.size(); k++){
                                    while (newLogin.equals(teachers.get(k).getLogin())){
                                        System.out.println("Input another login");
                                        newLogin = sc.next();
                                    }
                                }
                                for (int l = 0; l < students.size(); l++){
                                    while (newLogin.equals(students.get(l).getLogin())){
                                        System.out.println("Input another login");
                                        newLogin = sc.next();
                                    }
                                }
                                teachers.get(teachId).setLogin(newLogin);
                                System.out.println("Login successfull changed");
                                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teachers.txt", false));
                                oos.writeObject(teachers);
                            } catch (IndexOutOfBoundsException e) {
                                e.printStackTrace();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else if (instr2 == 2){
                            for (Teacher teacher: teachers){
                                System.out.println(teacher.toString1());
                            }
                            System.out.println("Input a teacher's ID");
                            int teachId = sc.nextInt();
                            try {
                                System.out.println("Input a new password");
                                String newPassword = sc.next();
                                teachers.get(teachId).setPassword(newPassword);
                                System.out.println("Password successfull changed");
                                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teachers.txt", false));
                                oos.writeObject(teachers);
                            } catch (IndexOutOfBoundsException e) {
                                e.printStackTrace();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else if (instr2 == 3){
                            for (Teacher teacher: teachers){
                                System.out.println(teacher.toString1());
                            }
                            System.out.println("Input a teacher's ID");
                            int teachId = sc.nextInt();
                            try {
                                System.out.println("Input a new status ([0] - banned [1] - default)");
                                int newStatus = sc.nextInt();
                                if (newStatus == 0 || newStatus == 1){
                                    teachers.get(teachId).setStatus(newStatus);
                                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teachers.txt", false));
                                    oos.writeObject(teachers);
                                } else System.out.println("Invalid input");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else if (instr2 == 4){
                            for (Teacher teacher: teachers){
                                System.out.println(teacher.toString1());
                            }
                            System.out.println("Input a teacher's ID");
                            int teachId = sc.nextInt();
                            try {
                                System.out.println("Input a new number");
                                String newNumber = sc.next();
                                teachers.get(teachId).setNumber(newNumber);
                                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teachers.txt", false));
                                oos.writeObject(teachers);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else if (instr2 == 5){
                            for (Teacher teacher: teachers){
                                System.out.println(teacher.toString1());
                            }
                            System.out.println("Input a teacher's ID");
                            int teachId = sc.nextInt();
                            try {
                                System.out.println("Input a new subject");
                                String newSubject = sc.next();
                                teachers.get(teachId).setSubject(newSubject);
                                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teachers.txt", false));
                                oos.writeObject(teachers);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else if (instr2 == 0){
                            action4 = false;
                        }
                        else System.out.println("Invalid input");
//                        int id, String fname, String lname, int age, String number, int status, String login, String password, String subject, ArrayList<String> grades
                    }
                }
                else if (instr == 2){
                    while (action5){
                        System.out.println("[1] Change login\n" +
                                "[2] Change password\n" +
                                "[3] Change status\n" +
                                "[4] Change number\n" +
                                "[5] Change group\n" +
                                "[0] Exit");
                        //int id String fname, String lname, int age, String number, int status, String login, String password, String group
                        int instr3 = sc.nextInt();
                        if (instr3 == 1){
                            for (Student student: students){
                                System.out.println(student.toString1());
                            }
                            System.out.println("Input a student's ID");
                            int studId = sc.nextInt();
                            try {
                                System.out.println("Input a new login");
                                String newLogin = sc.next();
                                for (int k = 0; k < teachers.size(); k++){
                                    while (newLogin.equals(teachers.get(k).getLogin())){
                                        System.out.println("Input another login");
                                        newLogin = sc.next();
                                    }
                                }
                                for (int l = 0; l < students.size(); l++){
                                    while (newLogin.equals(students.get(l).getLogin())){
                                        System.out.println("Input another login");
                                        newLogin = sc.next();
                                    }
                                }
                                students.get(studId).setLogin(newLogin);
                                System.out.println("Login successfull changed");
                                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt", false));
                                oos.writeObject(students);
                            } catch (IndexOutOfBoundsException e) {
                                e.printStackTrace();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else if (instr3 == 2){
                            for (Student student: students){
                                System.out.println(student.toString1());
                            }
                            System.out.println("Input a student's ID");
                            int studId = sc.nextInt();
                            try {
                                System.out.println("Input a new password");
                                String newPassword = sc.next();
                                students.get(studId).setPassword(newPassword);
                                System.out.println("Password successfull changed");
                                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt", false));
                                oos.writeObject(students);
                            } catch (IndexOutOfBoundsException e) {
                                e.printStackTrace();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else if (instr3 == 3){
                            for (Student student: students){
                                System.out.println(student.toString1());
                            }
                            System.out.println("Input a student's ID");
                            int studId = sc.nextInt();
                            try {
                                System.out.println("Input a new status ([0] - banned [1] - default)");
                                int newStatus = sc.nextInt();
                                if (newStatus == 0 || newStatus == 1){
                                    students.get(studId).setStatus(newStatus);
                                    System.out.println("Status successful changed");
                                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt", false));
                                    oos.writeObject(students);
                                } else System.out.println("Invalid input");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else if (instr3 == 4){
                            for (Student student: students){
                                System.out.println(student.toString1());
                            }
                            System.out.println("Input a student's ID");
                            int studId = sc.nextInt();
                            try {
                                System.out.println("Input a new number");
                                String newNumber = sc.next();
                                students.get(studId).setNumber(newNumber);
                                System.out.println("Number successful changed");
                                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt", false));
                                oos.writeObject(students);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else if (instr3 == 5){
                            for (Student student: students){
                                System.out.println(student.toString1());
                            }
                            System.out.println("Input a student's ID");
                            int studId = sc.nextInt();
                            try {
                                System.out.println("Input a new group");
                                String newGroup = sc.next();
                                students.get(studId).setGroup(newGroup);
                                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt", false));
                                oos.writeObject(students);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else if (instr3 == 0){
                            action5 = false;
                        } else System.out.println("Invalid input");
                    }
                }
                else if (instr == 3){
                    for (Teacher teacher: teachers){
                        if (teacher.getStatus() == 0){
                            System.out.println(teacher.toString());
                        }
                    }
                    for (Student student: students){
                        if (student.getStatus() == 0){
                            System.out.println(student.toString());
                        }
                    }
                }
                else if (instr == 0){
                    try {
                        start();
                        action3 = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }else System.out.println("Invalid input");
            }
        }
    }
}
