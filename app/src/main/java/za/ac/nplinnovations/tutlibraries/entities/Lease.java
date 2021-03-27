package za.ac.nplinnovations.tutlibraries.entities;

public class Lease {
    private int id;
    private String isbn;
    private String student_no;
    private String date;
    private String expiry_date;

    public Lease() {
    }

    public Lease(int id, String isbn, String student_no, String date, String expiry_date) {
        this.id = id;
        this.isbn = isbn;
        this.student_no = student_no;
        this.date = date;
        this.expiry_date = expiry_date;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }
}
