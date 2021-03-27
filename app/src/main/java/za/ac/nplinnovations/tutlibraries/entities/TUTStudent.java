package za.ac.nplinnovations.tutlibraries.entities;

import java.io.Serializable;

public class TUTStudent implements Serializable {
    private String student_no;
    private String firstname;
    private String lastname;
    private String email;
    private String course;
    private String address;

    public TUTStudent() {
    }

    public TUTStudent(String student_no, String firstname, String lastname, String email, String course, String address) {
        this.student_no = student_no;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.course = course;
        this.address = address;
    }

    public String getStudent_no() {
        return student_no;
    }

    public void setStudent_no(String student_no) {
        this.student_no = student_no;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "TUTStudent{" +
                "student_no='" + student_no + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", course='" + course + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
