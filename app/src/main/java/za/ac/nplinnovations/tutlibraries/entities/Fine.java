package za.ac.nplinnovations.tutlibraries.entities;

public class Fine {
    private int id;
    private String student_no;
    private String date;
    private String description;

    public Fine() {
    }

    public Fine(int id, String student_no, String date, String description) {
        this.id = id;
        this.student_no = student_no;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudent_no() {
        return student_no;
    }

    public void setStudent_no(String student_no) {
        this.student_no = student_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
