package za.ac.nplinnovations.tutlibraries.entities;

import java.io.Serializable;

public class Student implements Serializable {
    private String student_no;
    private String username;
    private String password;

    public Student() {
    }

    public Student(String student_no, String username, String password) {
        this.student_no = student_no;
        this.username = username;
        this.password = password;
    }

    public String getStudent_no() {
        return student_no;
    }

    public void setStudent_no(String student_no) {
        this.student_no = student_no;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
