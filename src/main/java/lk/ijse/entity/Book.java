package lk.ijse.entity;

import jakarta.persistence.*;
import lk.ijse.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "book")
public class Book {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private String id;

    @Column
    private String title;

    @Column
    private String author;

    @Column(name = "description")
    private String desc;

    @Column
    private String genre;

//    @Lob
//    @Column
//    private byte[] imageData;


    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "book")
    private List<BorrowedDetails> borrowedDetails = new ArrayList<>();

    public Book() {
    }

    public Book(String id, String title, String author, String desc, String genre, Branch branch, List<BorrowedDetails> borrowedDetails) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.desc = desc;
        this.genre = genre;
        this.branch = branch;
        this.borrowedDetails = borrowedDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<BorrowedDetails> getBorrowedDetails() {
        return borrowedDetails;
    }

    public void setBorrowedDetails(List<BorrowedDetails> borrowedDetails) {
        this.borrowedDetails = borrowedDetails;
    }

    public BookDto toDto(){
        BookDto bookDto = new BookDto();
        bookDto.setId(this.id);
        bookDto.setDesc(this.desc);
        bookDto.setTitle(this.title);
        bookDto.setAuthor(this.author);
        bookDto.setGenre(this.genre);

        bookDto.setBranchId(this.branch.getId());
        //bookDto.setBooksDetails(this.booksDetails);

        System.out.println(branch.getId());

        return bookDto;
    }
    //private byte[] image;
}
