package dborah.book_organizer.book_organizer.book_organizer.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dborah.book_organizer.book_organizer.location.Location;

@Entity
@Table
public class Book {
    //ATTRIBUTES
    @Id
    @SequenceGenerator(
        name="book_sequence",
        sequenceName="book_sequence",
        allocationSize=1
    )
    @GeneratedValue(
        strategy=GenerationType.SEQUENCE,
        generator="book_sequence"
    )
    private Long id;
    private String title;
    private String author;
    private Integer pageCount;
    private Integer width;
    @ManyToOne()
    private Location location;


    //CONSTRUCTORS
    public Book() {
    }

    public Book(String title, String author, Integer pageCount, Integer width, Location location) {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
        this.width = width;
        this.location = location;
    }


    //GETTERS AND SETTERS
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    //TOSTRING
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", author='" + getAuthor() + "'" +
            ", pageCount='" + getPageCount() + "'" +
            ", width='" + getWidth() + "'" +
            ", location='" + getLocation() + "'" +
            "}";
    }
}
