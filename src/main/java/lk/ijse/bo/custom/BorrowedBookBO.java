package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.BorrowedDetailsDto;
import lk.ijse.entity.Book;
import lk.ijse.entity.BorrowedDetails;

import java.util.List;

public interface BorrowedBookBO extends SuperBO {
    boolean saveBorrowedDetail(BorrowedDetailsDto dto);

    boolean updateBorrowedDetail(BorrowedDetailsDto dto);

    List<BorrowedDetailsDto> getAllBorrowed();

    List<BorrowedDetailsDto> getAllBorrowedFromUser(String userName);

    BorrowedDetails searchBorrowedDetails(String id, String userName);

    String getUserNameFromBorrowed(String bookId);

    boolean checkReturnDate(String bookId);

    List<BorrowedDetailsDto> getOverdueBorrowedDetails();

    List<BorrowedDetailsDto> getBorrowedDetailsNotReturned();

    List<Object[]> getUserAndBookTitle();

    boolean checkNumberOfBooks(String userName);

    int getBorrowedCount();

    int getOverDueCount();
}
