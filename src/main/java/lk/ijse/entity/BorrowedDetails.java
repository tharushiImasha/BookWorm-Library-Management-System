package lk.ijse.entity;

import jakarta.persistence.*;
import lk.ijse.embedded.BorrowedDetailPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "books_detail")
public class BorrowedDetails {

    @EmbeddedId
    private BorrowedDetailPK borrowedDetailPK;

    @CreationTimestamp
    @Column(name = "borrowed_date_time")
    private Timestamp borrowedDateTime;

    @Column(name = "due_date")
    private String dueDate;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", insertable = false, updatable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", insertable = false, updatable = false)
    private User user;
}
