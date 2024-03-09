package lk.ijse.entity;

import jakarta.persistence.*;
import lk.ijse.embedded.BooksDetailPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "books_detail")
public class BooksDetail {

    @EmbeddedId
    private BooksDetailPK booksDetailPK;

    @Column(name = "book_count")
    private int bookCount;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", insertable = false, updatable = false)        //pitin insert update krnn bri wenn insertable, updatable = false krnne
    private Book book;

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id", insertable = false, updatable = false)
    private Branch branch;
}
