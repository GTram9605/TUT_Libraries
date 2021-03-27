package za.ac.nplinnovations.tutlibraries.entities;

public class Payment {
    private int ID;
    private String student_no;
    private int fine_id;
    private String date;
    private String reference;

    public Payment() {
    }

    public Payment(int ID, String student_no, int fine_id, String date, String reference) {
        this.ID = ID;
        this.student_no = student_no;
        this.fine_id = fine_id;
        this.date = date;
        this.reference = reference;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStudent_no() {
        return student_no;
    }

    public void setStudent_no(String student_no) {
        this.student_no = student_no;
    }

    public int getFine_id() {
        return fine_id;
    }

    public void setFine_id(int fine_id) {
        this.fine_id = fine_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
