package za.ac.nplinnovations.tutlibraries.entities;

import java.util.UUID;

public class Message {
    private String ID;
    private String student_no;
    private String message;
    private String timestamp;
    private boolean is_from_student;

    public Message() {
    }

    public Message(String student_no, String message, String timestamp, boolean is_from_student) {
        this.ID = UUID.randomUUID().toString();
        this.student_no = student_no;
        this.message = message;
        this.timestamp = timestamp;
        this.is_from_student = is_from_student;
    }

    public boolean isIs_from_student() {
        return is_from_student;
    }

    public void setIs_from_student(boolean is_from_student) {
        this.is_from_student = is_from_student;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStudent_no() {
        return student_no;
    }

    public void setStudent_no(String student_no) {
        this.student_no = student_no;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
