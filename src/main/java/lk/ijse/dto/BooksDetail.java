package lk.ijse.dto;

import lk.ijse.embedded.BorrowedDetailPK;
import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BooksDetail {
    private BorrowedDetailPK borrowedDetailPK;

    private int bookCount;

    private Book book;

    private Branch branch;
}
