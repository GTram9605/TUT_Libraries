package za.ac.nplinnovations.tutlibraries.entities;

import java.io.Serializable;

public class Book implements Serializable {
    private String isbn;
    private String title;
    private String author;
    private String pages;
    private String image;

    public Book() {
    }

    public Book(String isbn, String title, String author, String pages, String image) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages='" + pages + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}
