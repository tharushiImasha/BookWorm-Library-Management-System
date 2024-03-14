package lk.ijse.repository.custom;

import lk.ijse.dto.BorrowedDetailsDto;
import lk.ijse.entity.Book;
import lk.ijse.entity.BorrowedDetails;
import lk.ijse.repository.CrudRepository;

import java.util.List;

public interface BorrowedBookRepository extends CrudRepository<BorrowedDetails> {
    List<BorrowedDetailsDto> getAllBorrowedFromUser(String userName);

    BorrowedDetails searchBorrowedDetails(String id, String userName);

    String getUserNameFromBorrowed(String bookId);

    boolean checkReturnDate(String bookId);

    List<BorrowedDetails> getOverdueBorrowedDetails();

    List<Object[]> getUserAndBookTitle();
}
