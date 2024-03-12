package lk.ijse.entity;

import jakarta.persistence.*;
import lk.ijse.dto.BorrowedDetailsDto;
import lk.ijse.dto.UserDto;
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
@Table(name = "borrowed_detail")
public class BorrowedDetails {

    @EmbeddedId
    private BorrowedDetailPK borrowedDetailPK;

    @CreationTimestamp
    @Column(name = "borrowed_date_time")
    private Timestamp borrowedDateTime;

    @Column(name = "due_date")
    private Timestamp dueDate;

    @Column(name = "return_date")
    private Timestamp returnDate;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", insertable = false, updatable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", insertable = false, updatable = false)
    private User user;

    public BorrowedDetails(BorrowedDetailPK borrowedDetailPK, Timestamp borrowedDateTime, Timestamp dueDate, Timestamp returnDate) {
        this.borrowedDetailPK = borrowedDetailPK;
        this.borrowedDateTime = borrowedDateTime;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public BorrowedDetailsDto toDto() {
        BorrowedDetailsDto borrowedDetailsDto = new BorrowedDetailsDto();
        borrowedDetailsDto.setBorrowedDetailPK(this.borrowedDetailPK);
        borrowedDetailsDto.setBorrowedDate(this.borrowedDateTime);
        borrowedDetailsDto.setDueDate(this.dueDate);
        borrowedDetailsDto.setReturnDate(this.getReturnDate());

        return borrowedDetailsDto;
    }
}
