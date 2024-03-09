package lk.ijse.entity;

import jakarta.persistence.*;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "book_id")
    private String id;

    @Column
    private String title;

    @Column
    private String author;

    @Column(name = "description")
    private String desc;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "book")
    private List<BooksDetail> booksDetails = new ArrayList<>();

    public BookDto toDto(){
        BookDto bookDto = new BookDto();
        bookDto.setId(this.id);
        bookDto.setDesc(this.desc);
        bookDto.setTitle(this.title);
        bookDto.setAuthor(this.author);
        bookDto.setBooksDetails(this.booksDetails);

        return bookDto;
    }


    //private byte[] image;
}
